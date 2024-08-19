package com.example.delivery.orderservice.application;

import com.example.delivery.orderservice.application.dto.OrderCommand;
import com.example.delivery.orderservice.application.dto.PaymentResponse;
import com.example.delivery.orderservice.application.dto.RestaurantApprovalResponse;
import com.example.delivery.orderservice.application.mapper.OrderDataMapper;
import com.example.delivery.orderservice.application.outbox.OrderPaymentOutboxMessage;
import com.example.delivery.orderservice.application.outbox.RestaurantApprovalOutboxMessage;
import com.example.delivery.orderservice.application.ports.output.OrderPaymentOutboxMessageRepository;
import com.example.delivery.orderservice.application.ports.output.OrderRepository;
import com.example.delivery.orderservice.application.ports.output.RestaurantApprovalOutboxMessageRepository;
import com.example.delivery.orderservice.application.ports.output.RestaurantRepository;
import com.example.delivery.orderservice.core.entity.Order;
import com.example.delivery.orderservice.core.entity.OrderItem;
import com.example.delivery.orderservice.core.entity.Restaurant;
import com.example.delivery.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class SagaHelper {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    private final OrderPaymentOutboxMessageRepository orderPaymentOutboxMessageRepository;
    private final RestaurantApprovalOutboxMessageRepository restaurantApprovalOutboxMessageRepository;
    private final ApplicationEventPublisher publisher;
    private final OrderDataMapper mapper;

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
        Order order = orderRepository.findById(paymentResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("order not found"));

        OrderPaymentOutboxMessage outboxMessage = orderPaymentOutboxMessageRepository.findById(
                paymentResponse.getId()
        ).orElseThrow(() -> new RuntimeException("outbox message not found"));
        // outbox completed
        orderPaymentOutboxMessageRepository
                .save(outboxMessage.updateStatus(OutboxStatus.COMPLETED));

        // restaurant Valid Check
        boolean isAvailable = checkRestaurant(order);
        if (isAvailable) {
            processOrder(order, paymentResponse);
        } else {
            rollbackPayment(order, paymentResponse);
        }

    }

    @Transactional(readOnly = true)
    public boolean checkRestaurant(Order order) {
        Restaurant restaurant = restaurantRepository.findById(
                order.getRestaurantId(),
                order.getOrderItems()
                        .stream().map(OrderItem::getProductId).toList()
        ).orElseThrow(() -> new RuntimeException("Restaurant Not Found"));
        return restaurant.isAvailable();
    }

    @Transactional
    public void processOrder(Order order, PaymentResponse paymentResponse) {
        UUID sagaId = paymentResponse.getSagaId();

        // paid
        order.pay();
        orderRepository.save(order);

        // restaurant outbox started
        restaurantApprovalOutboxMessageRepository
                .save(mapper.orderToRestaurantApprovalOutboxMessage(order, sagaId));

        // restaurant -> approval request
        publisher.publishEvent(
                mapper.orderToRestaurantApprovalEvent(order, sagaId)
        );
    }

    @Transactional
    public void rollbackPayment(Order order, PaymentResponse paymentResponse) {
        order.cancel();
        orderRepository.save(order);

        // Compensating Transaction
        publisher.publishEvent(
                mapper.responseToPaymentCompensateEvent(paymentResponse)
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

        // TODO : Optimistic Locking
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

        // TODO : Optimistic Locking
        restaurantApprovalOutboxMessageRepository
                .save(outboxMessage.updateStatus(OutboxStatus.COMPLETED));
    }

    @Transactional
    public void cancelOrder(RestaurantApprovalResponse restaurantApprovalResponse) {
        // restaurant approval -> Outbox Complete
        RestaurantApprovalOutboxMessage outboxMessage = restaurantApprovalOutboxMessageRepository
                .findBySagaId(restaurantApprovalResponse.getSagaId())
                .orElseThrow(() -> new RuntimeException("Outbox Message Not Found"));
        Order order = orderRepository.findById(restaurantApprovalResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        order.cancel();
        orderRepository.save(order);

        // Cancel Payment Request To Payment Service
        publisher.publishEvent(
                mapper.responseToPaymentCompensateEvent(restaurantApprovalResponse)
        );

        // TODO : Optimistic Locking
        restaurantApprovalOutboxMessageRepository
                .save(outboxMessage.updateStatus(OutboxStatus.COMPLETED));
    }
}
