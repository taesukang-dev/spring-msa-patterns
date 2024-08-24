# Spring MSA Patterns
- Spring 을 사용하여 여러가지 MSA Pattern 들을 구현
- read me 와 블로그 글의 내용이 다를 수 있으므로 블로그 글을 참고해주세요.

## 배달 시스템

<img src="./delivery/static/diagram.png" />

- <a href="https://liltdevs.tistory.com/219">블로그 글</a>
- <a href="https://github.com/taesukang-dev/spring-msa-patterns/tree/master/delivery">코드</a>
- 알아볼 수 있는 것
  - CQRS, CDC 활용 (Log Tailing Pattern, debezium)
  - Transactional Outbox Pattern (Polling Publisher Pattern)
  - Saga Pattern, Choreography

## 재고관리 시스템

<img src="./stock/static/diagram.png" />

- <a href="https://liltdevs.tistory.com/215">블로그 글</a>
- <a href="https://github.com/taesukang-dev/spring-msa-patterns/tree/master/stock">코드</a>
- 알아볼 수 있는 것
  - Locking 기법 (Distributed Lock, Optimistic Lock, CAS)
  - Redis Data 백업 방식 (Disk)
  - Transactional Outbox Pattern (Polling Publisher Pattern)

## 선착순 쿠폰 시스템

<img src="./coupon/img.png" />

- <a href="https://liltdevs.tistory.com/214">블로그 글</a>
- <a href="https://github.com/taesukang-dev/spring-msa-patterns/tree/master/coupon">코드</a>
- 알아볼 수 있는 것
  - Redisson Lock Acquire 구조(성능 위주)
  - Redis SortedSet, Set Computation Time
  - Kafka Messaging
