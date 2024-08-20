package com.example.delivery.orderservice.application.outbox;

import com.example.delivery.orderservice.application.MessageCallbackHelper;
import com.example.delivery.orderservice.application.mapper.OrderDataMapper;
import com.example.delivery.orderservice.application.ports.output.OrderPaymentMessagePublisher;
import com.example.delivery.orderservice.application.ports.output.OrderPaymentOutboxMessageRepository;
import com.example.delivery.orderservice.core.event.OrderPaymentEvent;
import com.example.delivery.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@EnableScheduling
@Component
public class OrderPaymentOutboxScheduler {

    private final OrderPaymentOutboxMessageRepository repository;
    private final OrderDataMapper mapper;
    private final OrderPaymentMessagePublisher publisher;
    private final MessageCallbackHelper helper;

    @Transactional
//    @Scheduled(fixedDelay = 3000L, initialDelay = 3000L)
    public void processOutboxMessage() {
        Optional<List<OrderPaymentOutboxMessage>> startedList = repository.findByStatus(OutboxStatus.STARTED);
        if (startedList.isPresent()) {
            List<OrderPaymentOutboxMessage> messages = startedList.get();
            messages.forEach(message -> publisher.publish(getPaymentEvent(message)));
        }
    }

    private OrderPaymentEvent getPaymentEvent(OrderPaymentOutboxMessage message) {
        return mapper.outboxMessageToOrderPaymentEvent(
                message,
                helper.applyCallback(
                        message,
                        repository::save
                )
        );
    }
}
