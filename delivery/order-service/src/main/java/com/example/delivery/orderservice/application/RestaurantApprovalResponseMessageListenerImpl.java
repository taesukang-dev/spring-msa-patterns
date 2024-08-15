package com.example.delivery.orderservice.application;

import com.example.delivery.infrastructure.vo.OrderStatus;
import com.example.delivery.orderservice.application.dto.RestaurantApprovalResponse;
import com.example.delivery.orderservice.application.mapper.OrderDataMapper;
import com.example.delivery.orderservice.application.outbox.OrderPaymentOutboxMessage;
import com.example.delivery.orderservice.application.outbox.RestaurantApprovalOutboxMessage;
import com.example.delivery.orderservice.application.ports.input.RestaurantApprovalResponseMessageListener;
import com.example.delivery.orderservice.application.ports.output.OrderApprovalOutboxMessageRepository;
import com.example.delivery.orderservice.application.ports.output.OrderPaymentOutboxMessageRepository;
import com.example.delivery.orderservice.application.ports.output.OrderRepository;
import com.example.delivery.orderservice.application.ports.output.RestaurantApprovalOutboxMessageRepository;
import com.example.delivery.orderservice.core.entity.Order;
import com.example.delivery.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprovalResponseMessageListener {

    private final OrderDataMapper mapper;
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher publisher;
    private final OrderPaymentOutboxMessageRepository orderPaymentOutboxMessageRepository;
    private final RestaurantApprovalOutboxMessageRepository restaurantApprovalOutboxMessageRepository;

    @Override
    // TODO : Move to Saga Helper
    // This method will be removed due to the use of the cqrs
    public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {
        // TODO : restaurant approval -> Outbox Complete
        RestaurantApprovalOutboxMessage outboxMessage = restaurantApprovalOutboxMessageRepository
                .findBySagaId(restaurantApprovalResponse.getSagaId())
                .orElseThrow(() -> new RuntimeException("Outbox Message Not Found"));
        Order order = orderRepository.findById(restaurantApprovalResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        OrderStatus orderStatus = outboxMessage.getOrderStatus();
        // It goes pending -> paid -> approved
        if (!orderStatus.equals(OrderStatus.PENDING)) {
            throw new RuntimeException("Not Available Order Status");
        }
        restaurantApprovalOutboxMessageRepository
                .save(outboxMessage.updateStatus(OutboxStatus.COMPLETED));

        // TODO : payment approval -> outbox started
        BigDecimal totalPrice = order.getTotalPrice();
        Long userId = order.getUserId();
        OrderPaymentOutboxMessage paymentOutboxMessage = orderPaymentOutboxMessageRepository.save(
                OrderPaymentOutboxMessage
                        .builder()
                        .id(UUID.randomUUID())
                        .sagaId(restaurantApprovalResponse.getSagaId())
                        .outboxStatus(OutboxStatus.STARTED)
                        .totalPrice(totalPrice)
                        .userId(userId)
                        .build()
        );
        // TODO : 1. pay
        publisher.publishEvent(
                mapper.outboxMessageToOrderPaymentEvent(paymentOutboxMessage)
        );
    }
    // TODO : 2. 주문

    @Override
    public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {

    }
}
