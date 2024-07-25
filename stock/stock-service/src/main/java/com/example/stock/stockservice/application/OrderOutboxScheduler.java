package com.example.stock.stockservice.application;

import com.example.stock.common.infrastructure.outbox.OutboxStatus;
import com.example.stock.stockservice.application.ports.output.OrderOutboxRepository;
import com.example.stock.stockservice.application.ports.output.OrderRequestMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//@EnableScheduling
@RequiredArgsConstructor
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
