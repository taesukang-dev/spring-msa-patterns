package com.example.stock.stockservice.application;

//@EnableScheduling
//@RequiredArgsConstructor
//@Component
public class OrderOutboxScheduler {

//    private final OrderOutboxRepository orderOutboxRepository;
//    private final OrderRequestMessagePublisher orderRequestMessagePublisher;

//    @Transactional
//    @Scheduled
    public void processOutboxMessages() {
        // STARTED 상태인 outbox message 를 가져온다.
//        orderOutboxRepository.findByStatus(OutboxStatus.STARTED);
        // publisher 로 다시 payment service 에 messaging 한다.
//        orderRequestMessagePublisher.publish(...);
    }

}
