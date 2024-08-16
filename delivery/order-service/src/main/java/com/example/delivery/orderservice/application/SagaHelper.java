package com.example.delivery.orderservice.application;

import com.example.delivery.infrastructure.vo.OrderStatus;
import com.example.delivery.orderservice.application.dto.OrderCommand;
import com.example.delivery.orderservice.application.dto.PaymentResponse;
import com.example.delivery.orderservice.application.dto.RestaurantApprovalResponse;
import com.example.delivery.orderservice.application.mapper.OrderDataMapper;
import com.example.delivery.orderservice.application.outbox.OrderPaymentOutboxMessage;
import com.example.delivery.orderservice.application.outbox.RestaurantApprovalOutboxMessage;
import com.example.delivery.orderservice.application.ports.output.OrderPaymentOutboxMessageRepository;
import com.example.delivery.orderservice.application.ports.output.OrderRepository;
import com.example.delivery.orderservice.application.ports.output.RestaurantApprovalOutboxMessageRepository;
import com.example.delivery.orderservice.core.entity.Order;
import com.example.delivery.outbox.OutboxStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class SagaHelper {
    private final OrderPaymentOutboxMessageRepository orderPaymentOutboxMessageRepository;
    private final RestaurantApprovalOutboxMessageRepository restaurantApprovalOutboxMessageRepository;
    private final OrderDataMapper mapper;
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public void startOrder(OrderCommand orderCommand) {
        Order order = mapper.orderCommandToOrder(orderCommand);
        order.validateOrder();
        order.initOrder();

        // 1. order 저장
        orderRepository.save(order);
        // 2. payment
        // payment -> outbox started
        BigDecimal totalPrice = order.getTotalPrice();
        Long userId = order.getUserId();
        OrderPaymentOutboxMessage paymentOutboxMessage = orderPaymentOutboxMessageRepository.save(
                OrderPaymentOutboxMessage
                        .builder()
                        .orderId(order.getId())
                        .id(UUID.randomUUID())
                        .sagaId(UUID.randomUUID())
                        .outboxStatus(OutboxStatus.STARTED)
                        .totalPrice(totalPrice)
                        .userId(userId)
                        .build()
        );
        // pay request
        publisher.publishEvent(
                mapper.outboxMessageToOrderPaymentEvent(paymentOutboxMessage)
        );
    }

    @Transactional
    public void completePayment(PaymentResponse paymentResponse) {
        UUID sagaId = paymentResponse.getSagaId();
        OrderPaymentOutboxMessage outboxMessage = orderPaymentOutboxMessageRepository.findById(
                paymentResponse.getId()
        ).orElseThrow(() -> new RuntimeException("outbox message not found"));
        Order order = orderRepository.findById(paymentResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("order not found"));

        // paid
        order.pay();
        orderRepository.save(order);

        // outbox completed
        orderPaymentOutboxMessageRepository
                .save(outboxMessage.updateStatus(OutboxStatus.COMPLETED));

        // TODO : Restaurant Valid Check
        // TODO : CDC

        // restaurant outbox started
        restaurantApprovalOutboxMessageRepository
                .save(mapper.orderToRestaurantApprovalOutboxMessage(order, sagaId));

        // restaurant -> approval request
        publisher.publishEvent(
                mapper.orderToRestaurantApprovalEvent(order, sagaId)
        );
    }

    @Transactional
    public void cancelPayment(PaymentResponse paymentResponse) {
        OrderPaymentOutboxMessage outboxMessage = orderPaymentOutboxMessageRepository.findById(
                paymentResponse.getId()
        ).orElseThrow(() -> new RuntimeException("outbox message not found"));
        Order order = orderRepository.findById(paymentResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("order not found"));

        order.cancel();
        orderRepository.save(order);
        orderPaymentOutboxMessageRepository.save(
                outboxMessage.updateStatus(OutboxStatus.COMPLETED)
        );
    }

    @Transactional
    public void completeOrder(RestaurantApprovalResponse restaurantApprovalResponse) {
        // restaurant approval -> Outbox Complete
        RestaurantApprovalOutboxMessage outboxMessage = restaurantApprovalOutboxMessageRepository
                .findBySagaId(restaurantApprovalResponse.getSagaId())
                .orElseThrow(() -> new RuntimeException("Outbox Message Not Found"));
        Order order = orderRepository.findById(restaurantApprovalResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        order.approve();
        orderRepository.save(order);

        restaurantApprovalOutboxMessageRepository
                .save(outboxMessage.updateStatus(OutboxStatus.COMPLETED));
    }
}
