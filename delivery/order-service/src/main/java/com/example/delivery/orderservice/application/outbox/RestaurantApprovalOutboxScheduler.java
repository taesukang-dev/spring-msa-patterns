package com.example.delivery.orderservice.application.outbox;

import com.example.delivery.orderservice.application.MessageCallbackHelper;
import com.example.delivery.orderservice.application.mapper.OrderDataMapper;
import com.example.delivery.orderservice.application.ports.output.OrderRepository;
import com.example.delivery.orderservice.application.ports.output.RestaurantApprovalMessagePublisher;
import com.example.delivery.orderservice.application.ports.output.RestaurantApprovalOutboxMessageRepository;
import com.example.delivery.orderservice.core.event.RestaurantApprovalEvent;
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
public class RestaurantApprovalOutboxScheduler {

    private final RestaurantApprovalOutboxMessageRepository repository;
    private final OrderRepository orderRepository;
    private final OrderDataMapper mapper;
    private final RestaurantApprovalMessagePublisher publisher;
    private final MessageCallbackHelper helper;

    @Transactional
//    @Scheduled(fixedDelay = 3000L, initialDelay = 3000L)
    public void processOutboxMessage() {
        Optional<List<RestaurantApprovalOutboxMessage>> startedList = repository.findByStatus(OutboxStatus.STARTED);
        if (startedList.isPresent()) {
            List<RestaurantApprovalOutboxMessage> messages = startedList.get();
            messages.forEach(message -> publisher.publish(getRestaurantApprovalEvent(message)));
        }
    }

    private RestaurantApprovalEvent getRestaurantApprovalEvent(RestaurantApprovalOutboxMessage message) {
        return mapper.orderToRestaurantApprovalEvent(
                orderRepository.findById(message.getOrderId())
                        .orElseThrow(() -> new RuntimeException("Order Not Found")),
                message.getSagaId(),
                helper.applyCallback(
                        message,
                        repository::save
                )
        );
    }
}
