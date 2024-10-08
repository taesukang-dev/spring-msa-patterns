package com.example.delivery.orderservice.application;

import com.example.delivery.orderservice.application.dto.OrderCommand;
import com.example.delivery.orderservice.application.dto.OrderItemCommand;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class SagaHelper {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    private final OrderPaymentOutboxMessageRepository orderPaymentOutboxMessageRepository;
    private final RestaurantApprovalOutboxMessageRepository restaurantApprovalOutboxMessageRepository;
    private final ApplicationEventPublisher publisher;
    private final OrderDataMapper mapper;
    private final MessageCallbackHelper helper;

    @Transactional
    // 1. 주문 생성, 결제 요청 : user -> order-service request
    public void startOrder(OrderCommand orderCommand) {
        Order order = mapper.orderCommandToOrder(orderCommand);
        Restaurant restaurant = checkRestaurant(order);
        if (!restaurant.isAvailable()) {
            throw new RuntimeException("Product Not Available");
        }

        // order 초기화
        order.setInfos(restaurant);
        order.validateOrder();
        order.initOrder();

        // 1. order 저장
        orderRepository.save(order);

        // 2. payment
        BigDecimal totalPrice = order.getTotalPrice();
        Long userId = order.getUserId();
        // payment -> outbox started
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
                mapper.outboxMessageToOrderPaymentEvent(
                        paymentOutboxMessage,
                        helper.applyCallback(
                                paymentOutboxMessage,
                                orderPaymentOutboxMessageRepository::save
                        )
                )
        );
    }

    @Transactional(readOnly = true)
    public Restaurant checkRestaurant(Order order) {
        return restaurantRepository.findById(
                order.getRestaurantId(),
                order.getOrderItems()
                        .stream().map(OrderItem::getProductId).toList()
        ).orElseThrow(() -> new RuntimeException("Restaurant Not Found"));
    }

    @Transactional
    // 2. 결제 승인 : payment-service -> order-service response
    public void completePayment(PaymentResponse paymentResponse) {
        Order order = orderRepository.findById(paymentResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("order not found"));
        processOrder(order, paymentResponse);
    }

    @Transactional
    // 3. 결제 승인, 주문 요청 : order-service -> restaurant-service request
    public void processOrder(Order order, PaymentResponse paymentResponse) {
        UUID sagaId = paymentResponse.getSagaId();

        // paid
        order.pay();
        orderRepository.save(order);

        // restaurant outbox started
        RestaurantApprovalOutboxMessage message = restaurantApprovalOutboxMessageRepository
                .save(mapper.orderToRestaurantApprovalOutboxMessage(order, sagaId));

        // restaurant -> approval request
        publisher.publishEvent(
                mapper.orderToRestaurantApprovalEvent(
                        order,
                        sagaId,
                        helper.applyCallback(
                                message,
                                restaurantApprovalOutboxMessageRepository::save
                        )
                )
        );
    }

    @Transactional
    // payment-service -> order-service response
    public void cancelPayment(PaymentResponse paymentResponse) {
        Order order = orderRepository.findById(paymentResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("order not found"));

        order.cancel();
        orderRepository.save(order);
    }

    @Transactional
    // 4. 주문 응답 : restaurant-service -> order-service response
    public void completeOrder(RestaurantApprovalResponse restaurantApprovalResponse) {
        Order order = orderRepository.findById(restaurantApprovalResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        order.approve();
        orderRepository.save(order);
    }

    @Transactional
    // restaurant-service -> order-service response
    public void cancelOrder(RestaurantApprovalResponse restaurantApprovalResponse) {
        Order order = orderRepository.findById(restaurantApprovalResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        order.cancel();
        orderRepository.save(order);

        // Cancel Payment Request To Payment Service
        publisher.publishEvent(
                mapper.responseToPaymentCompensateEvent(restaurantApprovalResponse)
        );
    }
}
