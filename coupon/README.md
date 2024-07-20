# 선착순 쿠폰 발급 시스템

<img src="./img.png" />

선착순 쿠폰 발급시스템의 요건은 다음과 같습니다.
1. 정해진 수량 만큼만 쿠폰을 발급해야 합니다.
2. 들어온 순서 대로 쿠폰을 발급해야 합니다. (First Come, First Served)
3. 단기간에 급증하는 트래픽을 견뎌야 합니다.

주어진 요건에 따라 기술적 해결책을 생각해보았습니다.
1. 정해진 수량
   1. 동시성을 제어한다.
   2. 즉, 두개의 요청이 하나의 공유 자원에 대해 변경을 요구하여도 일관성을 가져야한다.
   3. Atomic 한 처리가 필요하다.
2. 들어온 순서대로 쿠폰을 발급
   1. Http Request 에 대한 개별 Thread(Tomcat) 의 요청이 순서대로 수행되게 제어해야 한다.
3. 단기간에 급증하는 트래픽
   1. 위와 같이 동시성을 제어하며, 순서를 보장하면서도 급증하는 트래픽을 견뎌야 한다.
   2. 단순히 Scale-Up 하는 방향보다 Scale-Out 할 수 있는 시스템이 유리.
      1. Thread Pool, Connection Pool 등의 개수를 100만개 1000만개 만들어 둘 수 없으므로...

키워드는 `동시성 제어`, `순서 보장`, `Scale-Out` 이 될 것 같습니다.

<br />

## 동시성 제어
동시성 제어는 원자적 연산 보장으로 바꿔 말할 수 있을 것 같습니다.  
공유 데이터의 변화를 Thread Safe 하게 혹은 Thread 간 공유 데이터에 대해 경쟁할 수 없는 상태를 만드는 것입니다.  
이를 위해 주로 사용되는 동시성 제어 방법으로는 아래와 같은 것들이 있습니다.
1. Java
   1. Synchronized Block
   2. Reentrant Lock
   3. CAS
2. DB
   1. Pessimistic Lock
   2. Optimistic Lock
3. Redis
   1. Distributed Lock (Redisson)

각각의 방법에는 장단점이 있지만 현재 저희 요구에는 Scale Out 하는 것을 전제로 하였으니 여러 WAS를 병렬적 수행할 수 있게 하는 시스템이므로 Java 에서 제공하는 Locking 방법들은 사용하지 못할 것 같습니다.    
또한 DB Level 에서 지원하는 Locking 기법도 하나의 DB 에서 큰 트래픽을 받아내야 하고 Locking 하며 데드락의 위험이 있으니 SPOF 의 위험으로 사용이 어렵습니다.  

그렇다면 Redis 진영의 Locking 을 위한 라이브러리인 Redisson 이 남아있는데요.  
Redis 는 Single Thread 로 동작하며 하나의 요청에 Atomic한 수행을 보장하며 여러 WAS 에서 접근할 수 있고 DB 나 Application 의 책임을 분산할 수 있습니다.    
또한 Redisson 은 Locking 을 위해 편리한 API 를 제공하므로 Redisson 이 적합할 수도 있을 것 같습니다.  
다만, 성능의 측면에서 Redisson 은 전혀 유리하지 않습니다. (실제로 제가 테스트 했을 때 DB 의 비관적락과 비슷한 수준이었습니다.)   
Redisson 은 Spin Lock 의 형태를 지니지 않는, 다시 말해 Pub/Sub 기반의 NIO 로 빠른 성능을 지원할 것 같지만 꼭 그렇지만은 않습니다.  

<a href="https://github.com/redisson/redisson/blob/master/redisson/src/main/java/org/redisson/RedissonLock.java#L227"> Redisson tryLock Code </a> <br />
위 코드는 Redisson 의 tryLock 입니다. <br />  
CompletableFuture 를 서로 넘겨 주며 NIO 기반으로 동작하고 Semaphore 를 사용하여 Critical Section 으로의 접근을 제한하고 있습니다. <br />
다만, 여전히 남아있는 while(true) 가 눈에 띄기도 하고 아래 코드처럼 Thread 의 Lock Acquire 순서를 보장하기도 합니다.


```java
// https://github.com/redisson/redisson/blob/master/redisson/src/main/java/org/redisson/RedissonLock.java#L218
<T> RFuture<T> tryLockInnerAsync(long waitTime, long leaseTime, TimeUnit unit, long threadId, RedisStrictCommand<T> command) {
    return evalWriteSyncedAsync(getRawName(), LongCodec.INSTANCE, command,
            "if ((redis.call('exists', KEYS[1]) == 0) " +
                        "or (redis.call('hexists', KEYS[1], ARGV[2]) == 1)) then " +
                    "redis.call('hincrby', KEYS[1], ARGV[2], 1); " +
                    "redis.call('pexpire', KEYS[1], ARGV[1]); " +
                    "return nil; " +
                "end; " +
                "return redis.call('pttl', KEYS[1]);",
            Collections.singletonList(getRawName()), unit.toMillis(leaseTime), getLockName(threadId));
}
```
위 코드는 Lock 을 얻을 때 Redisson이 순서를 보장하는 로직이 담긴 Lua Script 입니다.  
가장 처음 들어온 Thread의 ID (Thread.currentThread.getId) 를 기준으로 +1 되는 Thread 를 받아들인다는 코드 입니다.  
따라서 가장 처음 들어온 Thread id가 1 일 때 Thread id 3이 요청한다면 거부하겠다는 것입니다.  
만약 Lock 을 얻겠다는 Thread 가 1, 2, 3 이라면 순서 대로 Lock 을 얻을 수 있겠지만 2, 9, 1 이라면 2의 TTL 까지 9, 1 이 기다리게 되는 Convoy Effect 의 우려가 있습니다.  

정리하면 Redisson 은 Redis 의 이점을 갖고 있음에도 Spin Lock 을 일부 차용하고 순서를 보장하는 절차가 추가되면서 생각보다 빠른 Lock Acquire - Release 는 불가능 해보입니다.  
그럼에도 불구하고 Redisson 은 DB 의 Locking 책임을 분산할 수 있으니 여전히 유용합니다. 이는 DB의 부하 뿐만 아니라 Dead lock 의 위험도 줄여 줍니다.  

하지만 이번에는 Redisson 을 사용하지 않을 것입니다.  
Redisson 이 Locking 하는 범위를 최대한 좁힌다면 성능과 관련된 이슈도 피할 수 있을 것 같지만 그보다 발급 검증 서버와 발급 서버를 따로 두기로 해보겠습니다.

<br />

## 들어온 순서 대로 쿠폰을 발급
들어온 순서에 대한 보장은 요청한 시간을 기준으로 혹은 서버에서 요청을 받은 시간을 기준으로 (클라이언트 마다 통신 상태가 다를 수 있으므로) 해볼 수 있을 것 같습니다.  
이때 redis 의 Sorted Set 을 고려할 수 있을 것 같습니다.  
Sorted Set 으로 Element 의 유일성을 보장하면서 Timestamp 값을 기준으로 Score 를 매겨 순서를 보장할 수 있습니다.  
즉 가장 낮은 값의 Timestamp 혹은 Epoch Time 을 갖는 Element 가 가장 높은 우선 순위를 갖게 되는 것입니다.  

만약 클라이언트의 요청을 기준으로 순번을 매긴다면, Timestamp 를 매긴다면 위에서 언급했듯 통신 지연으로 인해 이미 다 쿠폰을 발급했음에도 더 낮은 우선수위를 갖게되는 경우가 발생할 수 있을 것 같습니다.  
다시 말해 나중에 들어온 Request 가 더 낮은 Epoch Time 을 가질 수 있습니다. 이때 이미 정렬된 상태가 흐트러지게 됩니다.  
이를 위해 계수를 두는 등 여러 해법이 있겠지만 지나치게 관리 포인트가 늘어나게 될 것 같습니다.

이를 위해 서버에서 시간을 매길 수 있겠습니다.  
사용자에겐 미안하지만 요청을 먼저 받은 순서 대로 쿠폰을 발급할 수 밖에 없겠습니다.  
하지만 이때에도 여전히 고민이 있습니다. 
SortedSet 의 zadd 는 O(log^n) 의 복잡도를 가지고 있습니다. 즉 추가되면 추가될 수록 느려지게 됩니다.  

그렇다면 위와 같은 SortedSet 을 사용하는 것 보다 Set 을 사용하는 것이 더 유리해보입니다.  
sismemeber, sadd, scard 는 O(1) 로 아주 빠른 속도를 갖고 있기 때문입니다.  
client 의 요청이 들어왔을 때 sismember 로 이미 발급이 되었는지 확인하고, scard 로 총 개수를 확인하고 그 이후 sadd 로 발급 set 에 추가한다면 가능할 것 같습니다.

그리고 위와 같은 연산은 Lua Script 로 작성한다면 Atomic 하면서도 빠른 속도를 지원할 수 있게 될 것 같습니다.
```java
private RedisScript<String> issueRequestScript() {
  String script = """
          if redis.call('SISMEMBER', KEYS[1], ARGV[1]) == 1 then
              return '2'
          end
                          
          if tonumber(ARGV[2]) > redis.call('SCARD', KEYS[1]) then
              redis.call('SADD', KEYS[1], ARGV[1])
              return '1'
          end
                          
          return '3'
          """;
  return RedisScript.of(script, String.class);
}
```

위와 같은 방법들로 동시성을 제어했고, 순서를 보장하였습니다. 단기간에 급증하는 트래픽을 위한 Scale-Out 은 어떻게 할 수 있을까요?

<br />

## 단기간에 급증하는 트래픽
단순히 생각한다면 쿠폰 발급은 아래와 같은 순서로 할 수 있습니다.
1. 쿠폰 발급을 요청한다. (client)
2. 쿠폰 발급을 검증한다. (server)
   1. DB 혹은 Cache에서 쿠폰을 가져온다.
3. 쿠폰을 발급한다. (server)
   1. DB 에 쿠폰 발급 이력을 저장한다.

2 - 3 순서를 하나의 WAS 에서 처리해도 되지만 이것을 분리한다면 하나의 Transaction에서 DB 와의 통신(IO Event)을 절반으로 줄일 수 있게 될 것입니다.  
즉, 한 쪽에서는 지속적으로 쿠폰에 대한 검증을 처리하고, 한 쪽에서는 지속적으로 쿠폰을 발급하며 병렬처리를 유도하는 것입니다.  
그러므로 더 많은 트래픽 처리량을 기대할 수 있게 됩니다.  
비유하자면, Lock Striping 처럼 하나의 역할 안에서도 잘게 쪼개어 보는 것입니다.  

이때 server 와 server 의 통신이 필요하므로 통신 수단에 대해 고려해보겠습니다.   
1. HTTP
2. gRPC
3. Messaging Queue
   1. RabbitMQ
   2. Kafka

성능 얘기 이전에 HTTP 와 gRPC 는 Connetion Timeout 등 데이터 유실 가능성이 있어 보입니다.  
물론 재요청 처리에 대한 로직을 작성할 수 있지만 이런 이벤트성 서비스에는 적합해 보이지 않습니다. (이를테면 Kafka 는 Pull 방식이므로 무조건 가용한 WAS 가 메세지를 가져갑니다.)  
그렇다면 RabbitMQ 와 Kafka 에 대한 비교로 이어질 수 있는데요. 이번 서비스에서는 Kafka 를 적용하기로 하였고 이유는 다음과 같습니다.  
1. 대규모 트래픽을 견뎌야 합니다.
2. Kafka 는 잘 알려져 있듯이 RabbitMQ 보다 높은 처리량을 지원합니다. (Kafka 1m/s, RabbitMQ 10k/s)
3. RabbitMQ 는 Push 방식으로 메세지를 전달하고 Kafka 는 pull 방식으로 Backpressure(Batch Size 등과 같은 조건으로) 를 지원합니다.

RabbitMQ 는 지정된 수신인에게 정확이 메시징 하는 것에 초점을 맞추는 반면 Kafka 는 병렬 처리에 대한 강점을 보입니다.  
위의 특성들로 미루어보아 단기간에 급증하는 트래픽을 위해서는 Kafka 가 더 적합해 보입니다.  

따라서 2번 (쿠폰 발급을 검증) 처리가 끝난다면 쿠폰 검증 WAS(coupon-service) 에서 쿠폰 발급 WAS(coupon-consumer) 로 Kafka Messaging 을 하게 되어 3번 (쿠폰 발급) 처리를 하게 됩니다.  
그렇다면 한 쪽에서는 지속적으로 검증하고 한 쪽에서는 지속적으로 발급하며 쿠폰 검증/발급에 대해 WAS 간 병렬처리가 가능하게 됩니다.  

- 쿠폰 발급 요청 Publish

```java
// https://github.com/taesukang-dev/spring-msa-patterns/blob/master/coupon/coupon-service/src/main/java/com/example/coupon/couponcore/messaging/publisher/kafka/CouponIssueRequestKafkaPublisher.java

@Slf4j
@RequiredArgsConstructor
@Component
public class CouponIssueRequestKafkaPublisher implements CouponIssueRequestMessagePublisher {

    private final KafkaTemplate<String, CouponIssueAvroModel> kafkaTemplate;
    private final CouponIssueMessagingMapper couponIssueMessagingMapper;

    @Override
    public void publish(CouponIssueEvent couponIssueEvent) {
        kafkaTemplate.send(COUPON_ISSUE_TOPIC,
                couponIssueMessagingMapper.couponIssueEventToAvroModel(couponIssueEvent));
    }
}
```

- 쿠폰 발급 요청 수신 Listener

```java
// https://github.com/taesukang-dev/spring-msa-patterns/blob/master/coupon/coupon-consumer/src/main/java/com/example/coupon/couponconsumer/messaging/listener/kafka/CouponIssueRequestKafkaListener.java

@RequiredArgsConstructor
@Component
public class CouponIssueRequestKafkaListener {

    private final IssueRequestMessageListener issueRequestMessageListener;
    private final IssueRequestMessagingMapper mapper;

    @KafkaListener(topics = COUPON_ISSUE_TOPIC, groupId = "spring")
    public void consumer(@Payload CouponIssueAvroModel messages) {
        issueRequestMessageListener.completeIssueRequest(
                mapper.issueRequestAvroModelToIssueRequest(messages)
        );
    }
}
```

## 정리
선착순 쿠폰 발급 서비스를 위한 요건에 대한 저의 답변은 다음과 같습니다.
1. 정해진 수량 만큼만 쿠폰 발급 ⇒ Redis 에서 계수를 두어 판단
2. 들어온 순서 대로 쿠폰 발급 ⇒ Redis Set 과 Lua Script 를 사용
3. 단기간에 급증하는 트래픽 ⇒ WAS 의 역할을 분리하여 Messaging 처리

끝!
