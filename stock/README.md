# 재고 관리 시스템

<img src="./static/diagram.png" />

재고 관리 시스템의 요건은 다음과 같습니다.
1. 정해진 재고 만큼만 주문이 이뤄져야 합니다. 
   1. 이때 주문은 아주 민감하게 이뤄져야 합니다. Hello World 에서 일어난 일이 Real World 에 영향을 미쳐서는 안 됩니다.
   2. 주문 처리가 제대로 되지 않는다면 이를 위한 CS 비용, 재무적 책임 등 cost 가 높습니다.
2. 주문과 결제는 무조건 성공하거나 무조건 실패해야 합니다.
   1. 어떤 경우에도 {주문 실패, 결제 성공}과 같은 상태 불일치 pair 는 발생해서는 안됩니다.
3. 주문과 결제는 시간이 너무 오래 걸려서는 안됩니다.
   1. 사용자 경험을 저해할수록 사용자는 이탈하게 될 가능성이 높습니다.
   2. 주문과 결제가 아주 많은 트래픽이 있을 가능성은 낮지만 그럼에도 불구하고 원활한 환경을 제공해야 합니다.

주어진 요건에 따라 기술적 요건을 생각해보았습니다.

1. 정해진 재고 만큼만 주문
   1. 동시성을 제어한다.
   2. 재고에 대한 Atomic 한 처리가 필요하다.
   3. 다만 재고는 Durability 가 굉장히 중요하다. ⇒ 요건 1-1
   4. 이전 Coupon System 에서 했던 Redis Set 을 이용하는 방식은 한계점이 존재한다.
   5. 재고에 Locking 하면서도 갱신 손실을 대비하기 위한 CAS 연산이 필요하다.
2. 주문과 결제는 무조건 성공하거나 무조건 실패.
   1. 주문과 결제가 하나의 Transaction 이 되어야 함.
   2. 주문 OK ⇒ 결제 OK, 주문 FAIL ⇒ 결제 FAIL 을 무조건 지켜야 한다.
3. 주문과 결제는 시간이 너무 오래 걸려서는 안됨.
   1. 위와 같은 요건들을 만족하면서도 원활한 사용자 경험을 제공해야 함.
   2. 이때 결제는 HTTP Request 가 필요하다.
      1. 결제사, PG...
   3. 외부와 통신하면서도 성능과 Transaction 을 유지해야함. ⇒ Transactional Outbox Pattern

키워드는 `동시성 제어(내구성에 유의)`, `Transaction`, `Scale-Out (Transactional Outbox Pattern)` 이 될 것 같습니다.

## 정해진 재고 만큼만 주문 (동시성 제어, 내구성에 유의)

이전 쿠폰 시스템에서는 여러가지 동시성 기법을 알아보며 결과적으로 Redis Set 을 활용하는 방법을 선택하였습니다.  
사용자 요청을 서버에서 받으면 쿠폰 발행량을 체크하여 Redis Set 사이즈와 비교한 뒤 Set 의 사이즈가 쿠폰 발행량을 넘지 않았다면 Set 에 사용자를 추가하여 발행하는 방식이었는데요.  
데이터 내구성이 중요해진 만큼 위와 같은 방식은 사용하지 못할 것 같습니다.

Redis 는 데이터를 저장하며 AOF/RDB 라는 방식으로 백업합니다.
RDB 는 메모리에 있는 전체 데이터를 특정 주기로 백업하는 방식을 취합니다.   
이때 무작정 전부를 백업하는 것이 아닌, Copy On Write 방식을 취합니다.  
Copy On Write 는 Parent Process 의 메모리를 모두 가져와 자신의 것으로 만드는 것이기 때문에 백업하는 순간 Redis 의 메모리 사용량은 2배가 되고, 이는 SWAP 의 위험이 있습니다.  

AOF 는 Buffer 에 데이터를 쓰고 데이터 변경 이후 Logging 하는 방식입니다.  
따라서 만약 Buffer 에는 데이터가 쓰여졌지만 Redis 가 장애로 Down 됐고 Failback 했을 때 해당 데이터는 Logging 되지 않았기 때문에 복구할 수 없습니다.  
이 또한 특정 주기로 이뤄지지만 매 Command 마다 Logging 할 수 있는 옵션도 있습니다.  
다만 매 Command 마다 Logging 하게 된다면 매 Command 는 Disk IO 로 Redis 가 갖고 있는 빠른 성능에 대한 장점이 희석됩니다.  

따라서, redis 에서 계수를 사용하는 방식은 redis 가 장애 발생으로 내려간다면 데이터 유실 가능성이 있고   
총 재고 10개일 때, 실제 DB에 저장된 order 는 10개이나, 장애 발생으로 redis 는 9개라고 알고있고 이때 다시 주문이 들어온다면 11개가 되는 불상사가 일어날 수 있습니다.   
이를 위해 redis 의 cluster / sentinel 을 사용할 수 있지만 (우선 데이터를 slave 에 보낸 뒤에 aof 에 쓰기 때문에)   
redis 는 async 방식으로 replication 하며 최초 sync 이후 모든 write 명령을 slave 에 전송하는데   
이때 slave 는 데이터를 받았는지 전송 결과를 ack 하지 않기 때문에 데이터 gap 은 분명 존재할 수 있습니다.  

이런 경우 MySQL 에서는 innodb_flush_log_at_trx_commit, rpl_semi_sync_master_wait_point 옵션을 사용하여 제어할 수 있습니다.    
innodb_flush_log_at_trx_commit=0 옵션을 사용하여 모든 변경사항을 Disk 에 저장한 뒤에 사용자에게 응답하게 되고, (물론 성능적 저하는 있습니다.)  
rpl_semi_sync_master_wait_point=after_sync 옵션을 사용한다면 우선 replica 에게 데이터를 보내고 commit 하게 되므로 장애 발생하여 failover 하게 되면 해당 데이터를 갖고 있는 node 가 master 가 되며 eventual consistency 를 만족하게 됩니다.  

따라서 Redis 에서 제어하는 것보다 MySQL 혹은 Database 를 사용하는 제어 방식이 데이터 내구성 측면에서는 유리해보입니다.   
이때 무조건 Distributed Lock 을 사용하기보다 왜 Database 의 X Lock 은 안되는가에 대해 생각해보고자 합니다.  
MySQL Repeatable Read 수준에서 X Lock 은 원자적 연산을 보장하기 때문에 단순히 동시성을 제어하기 위해서 Redisson이 필요할까에 대한 고찰입니다.  

이를테면 아래와 같은 상황입니다.  

<img src="./static/mysql-xlock.png" />

1. 왼쪽, 오른쪽 세션에서 트랜잭션을 함께 시작했습니다.
2. 왼쪽과 오른쪽 세션은 number 가 0 임을 확인합니다.
3. 왼쪽 세션에서 update 하고 select 했을 때 1임을 확인하고 commit 합니다.
4. 오른쪽 세션에서 update 하고 select 했을 때 2임을 확인합니다.
   1. 이때 왼쪽 세션에서 다시 select 한다고 해도 오른쪽 세션은 commit 하지 않았기 때문에 아직 number 는 1 입니다.
5. 오른쪽 세션에서 commit 하고 왼쪽 오른쪽 세션은 모두 number 가 2임을 확인합니다.

저러한 특성에 기인한다면 원자성을 보장하므로 Distributed Lock 을 사용하지 않아도 될 것 처럼 보입니다.  
하지만 여전히 Redisson 은 유용합니다.  
X Lock 은 배타적 특성을 갖고 있기 때문에 Deadlock 의 발생 확률이 높기 때문입니다.  
그에 반해 Redisson 은 Redis 의 원자적 특성과 NIO 기반으로 동작하는 Pub/Sub 기반의 Locking 기법으로 Database 의 책임을 분할합니다.  

따라서 MySQL 의 X-Lock 을 사용했을 때 위의 장애 상황으로 인해 DB 가 down 된다면 서비스 자체가 불가능해지지만,  
Redisson 을 사용한다면 DB 의 접근은 가능하기 때문에 주문과 관련된 서비스는 장애가 발생할 수 있지만 다른 서비스는 지속적인 서비스가 가능합니다.  

하지만, Redisson 을 사용하며 여전히 예측할 수 있는 장애상황이 있습니다.  
Redisson 을 사용하며 하나의 트랜잭션이 무한정 Lock 을 획득할 수 없게 Lock Timeout 을 설정하게 되는데요.  
이때 갱신 손실이 발생하게 될 수 있습니다.

<img src="./static/lost-update.png" />

1. A가 락을 획득하고 B 는 대기합니다.
2. A가 모종의 이유로 Lock 이 Timeout 으로 획득을 해제하고 이때 B 가 획득합니다.
3. B 는 현재 재고를 기준으로 -1 연산합니다.
4. A 의 네트워크가 다시 정상화 되어 당시 재고를 기준으로 -1 연산합니다.

Thread 경합과 관련된 예제와 비슷한 상황입니다.  
따라서 같은 상태에 대한 Versioning 을 통해 이러한 상황을 방지해야 합니다.  
이는 Optimistic Locking 으로 할 수 있습니다.  

```java
// StockServiceHelper.java
@DistributedLock
private void checkQuantityAndUpdate(StockBuyCommand stockBuyCommand) {
    Stock stock = stockRepository.findById(stockBuyCommand.productId())
            .orElseThrow(() -> new RuntimeException("Item Not Found"));
    if (!stock.isAvailableToBuy(stockBuyCommand.quantity())) {
        throw new RuntimeException("Not available to buy");
    }

    int updateQuantity = stock.getAvailableQuantity() - stockBuyCommand.quantity();
    stock.updateAvailableQuantity(updateQuantity);

    try {
        stockRepository.save(stock);
    } catch (OptimisticLockingFailureException e) {
        throw new RuntimeException("Lost Update Occurred");
    }
}
```

위와 같이 stock(재고) 에 대해 Distributed Locking 을 하면서도 stock 을 update 하는 순간에 혹시 모를 갱신 손실을 대비하는 것입니다.  
정리하면, RDB 를 사용하여 내구성을 지원하면서도 Locking 책임을 분산하기 위해 Redisson 을 사용하며 혹시 모를 갱신 손실을 대비하여 Optimistic Locking 을 하는 것입니다.  

추가로 궁금하신 사항은 아래를 확인해주세요!
- <a href="https://liltdevs.tistory.com/192">MySQL Locking</a>
- <a href="https://liltdevs.tistory.com/198">redisson</a>

## 주문과 결제는 무조건 성공하거나 무조건 실패(Transaction)

2 -> eventual consystency

주문이 실패했는데 결제요청이 가서는 안됨 -> spring event after commit