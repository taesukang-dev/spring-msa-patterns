package com.example.delivery.orderservice.application.mapper;

import com.example.delivery.orderservice.application.dto.OrderCommand;
import com.example.delivery.orderservice.application.dto.OrderItemCommand;
import com.example.delivery.orderservice.application.dto.PaymentResponse;
import com.example.delivery.orderservice.application.dto.RestaurantApprovalResponse;
import com.example.delivery.orderservice.application.outbox.OrderPaymentOutboxMessage;
import com.example.delivery.orderservice.application.outbox.RestaurantApprovalOutboxMessage;
import com.example.delivery.orderservice.core.entity.Order;
import com.example.delivery.orderservice.core.entity.OrderItem;
import com.example.delivery.orderservice.core.entity.Product;
import com.example.delivery.orderservice.core.entity.Restaurant;
import com.example.delivery.orderservice.core.event.CancelOrderEvent;
import com.example.delivery.orderservice.core.event.OrderPaymentEvent;
import com.example.delivery.orderservice.core.event.PaymentCompensateEvent;
import com.example.delivery.orderservice.core.event.RestaurantApprovalEvent;
import com.example.delivery.outbox.OutboxStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OrderDataMapper {

    public Order orderCommandToOrder(OrderCommand orderCommand) {
        return Order
                .builder()
                .userId(orderCommand.userId())
                .restaurantId(orderCommand.restaurantId())
                .totalPrice(orderCommand.totalPrice())
                .orderItems(orderCommand.items().stream().map(this::orderItemCommandToOrderItem).collect(Collectors.toList()))
                .deliveryAddress(orderCommand.deliveryAddress())
                .build();
    }

    private OrderItem orderItemCommandToOrderItem(OrderItemCommand orderItemCommand) {
        return OrderItem
                .builder()
                .productId(orderItemCommand.productId())
                .quantity(orderItemCommand.quantity())
                .price(orderItemCommand.price())
                .subTotal(orderItemCommand.subTotal())
                .build();
    }

    public RestaurantApprovalOutboxMessage orderToRestaurantApprovalOutboxMessage(Order order, UUID sagaId) {
        return RestaurantApprovalOutboxMessage.builder()
                .id(UUID.randomUUID())
                .sagaId(sagaId)
                .orderId(order.getId())
                .userId(order.getUserId())
                .restaurantId(order.getRestaurantId())
                .trackingId(order.getTrackingId())
                .orderStatus(order.getOrderStatus())
                .outboxStatus(OutboxStatus.STARTED)
                .build();
    }

    public RestaurantApprovalEvent orderToRestaurantApprovalEvent(
            Order order,
            UUID sagaId,
            BiConsumer<SendResult<String, ?>, Throwable> callback
    ) {
        return RestaurantApprovalEvent.builder()
                .sagaId(sagaId)
                .orderId(order.getId())
                .userId(order.getUserId())
                .restaurantId(order.getRestaurantId())
                .deliveryAddress(order.getDeliveryAddress())
                .totalPrice(order.getTotalPrice())
                .productIds(
                        order.getOrderItems()
                                .stream().map(OrderItem::getProductId)
                                .collect(Collectors.toList())
                )
                .trackingId(order.getTrackingId())
                .orderStatus(order.getOrderStatus())
                .callback(callback)
                .build();
    }

    public OrderPaymentEvent outboxMessageToOrderPaymentEvent(
            OrderPaymentOutboxMessage message,
            BiConsumer<SendResult<String, ?>, Throwable> callback
    ) {
        return OrderPaymentEvent.builder()
                .id(message.getId())
                .orderId(message.getOrderId())
                .sagaId(message.getSagaId())
                .createdAt(message.getCreatedAt())
                .outboxStatus(message.getOutboxStatus())
                .totalPrice(message.getTotalPrice())
                .userId(message.getUserId())
                .callback(callback)
                .build();
    }

    public PaymentCompensateEvent responseToPaymentCompensateEvent(PaymentResponse response) {
        return PaymentCompensateEvent.builder()
                .orderId(response.getOrderId())
                .sagaId(response.getSagaId())
                .userId(response.getUserId())
                .result(response.isResult())
                .build();
    }

    public CancelOrderEvent responseToPaymentCompensateEvent(RestaurantApprovalResponse response) {
        return CancelOrderEvent.builder()
                .orderId(response.getOrderId())
                .sagaId(response.getSagaId())
                .userId(response.getUserId())
                .result(response.isResult())
                .build();
    }


}
