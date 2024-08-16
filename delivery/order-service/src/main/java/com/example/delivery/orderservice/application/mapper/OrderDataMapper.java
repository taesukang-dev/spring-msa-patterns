package com.example.delivery.orderservice.application.mapper;

import com.example.delivery.orderservice.application.dto.OrderCommand;
import com.example.delivery.orderservice.application.dto.OrderItemCommand;
import com.example.delivery.orderservice.application.outbox.OrderPaymentOutboxMessage;
import com.example.delivery.orderservice.application.outbox.RestaurantApprovalOutboxMessage;
import com.example.delivery.orderservice.core.entity.Order;
import com.example.delivery.orderservice.core.entity.OrderItem;
import com.example.delivery.orderservice.core.event.OrderPaymentEvent;
import com.example.delivery.orderservice.core.event.RestaurantApprovalEvent;
import com.example.delivery.outbox.OutboxStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

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
                .sagaId(sagaId)
                .orderId(order.getId())
                .userId(order.getUserId())
                .restaurantId(order.getRestaurantId())
                .trackingId(order.getTrackingId())
                .orderStatus(order.getOrderStatus())
                .outboxStatus(OutboxStatus.STARTED)
                .build();
    }

    public RestaurantApprovalEvent orderToRestaurantApprovalEvent(Order order, UUID sagaId) {
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
                .build();
    }

    public OrderPaymentEvent outboxMessageToOrderPaymentEvent(OrderPaymentOutboxMessage message) {
        return OrderPaymentEvent.builder()
                .id(message.getId())
                .orderId(message.getOrderId())
                .sagaId(message.getSagaId())
                .createdAt(message.getCreatedAt())
                .outboxStatus(message.getOutboxStatus())
                .totalPrice(message.getTotalPrice())
                .userId(message.getUserId())
                .version(message.getVersion())
                .build();
    }


}
