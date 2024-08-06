package com.example.delivery.orderservice.dataaccess.mapper;

import com.example.delivery.orderservice.application.outbox.OrderApprovalOutboxMessage;
import com.example.delivery.orderservice.application.outbox.RestaurantApprovalOutboxMessage;
import com.example.delivery.orderservice.core.entity.Order;
import com.example.delivery.orderservice.core.entity.OrderItem;
import com.example.delivery.orderservice.dataaccess.entity.OrderEntity;
import com.example.delivery.orderservice.dataaccess.entity.OrderItemEntity;
import com.example.delivery.orderservice.dataaccess.entity.outbox.OrderApprovalOutboxMessageEntity;
import com.example.delivery.orderservice.dataaccess.entity.outbox.RestaurantApprovalOutboxMessageEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataAccessMapper {

    public OrderEntity orderToOrderEntity(Order order) {
        return OrderEntity
                .builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .restaurantId(order.getRestaurantId())
                .trackingId(order.getTrackingId())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getOrderStatus())
                .deliveryAddress(order.getDeliveryAddress())
                .items(
                        order.getOrderItems()
                                .stream().map(this::orderItemToOrderItemEntity)
                                .collect(Collectors.toList())
                )
                .build();
    }

    public Order orderEntityToOrder(OrderEntity orderEntity) {
        return Order.builder()
                .id(orderEntity.getOrderId())
                .userId(orderEntity.getUserId())
                .restaurantId(orderEntity.getRestaurantId())
                .deliveryAddress(orderEntity.getDeliveryAddress())
                .totalPrice(orderEntity.getTotalPrice())
                .orderItems(
                        orderEntity.getItems()
                                .stream().map(this::orderItemEntityToOrderItem)
                                .collect(Collectors.toList())
                )
                .trackingId(orderEntity.getTrackingId())
                .orderStatus(orderEntity.getOrderStatus())
                .build();
    }

    private OrderItemEntity orderItemToOrderItemEntity(OrderItem orderItem) {
        return OrderItemEntity
                .builder()
                .orderItemId((long) orderItem.getOrderItemId())
                .productId(orderItem.getProductId())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .subTotal(orderItem.getSubTotal())
                .build();
    }

    private OrderItem orderItemEntityToOrderItem(OrderItemEntity orderItemEntity) {
        return OrderItem
                .builder()
                .orderItemId(orderItemEntity.getOrderItemId().intValue())
                .orderId(orderItemEntity.getOrder().getOrderId())
                .productId(orderItemEntity.getProductId())
                .price(orderItemEntity.getPrice())
                .quantity(orderItemEntity.getQuantity())
                .subTotal(orderItemEntity.getSubTotal())
                .build();
    }

    public RestaurantApprovalOutboxMessageEntity restaurantOutboxMessageToEntity(RestaurantApprovalOutboxMessage outboxMessage) {
        return RestaurantApprovalOutboxMessageEntity
                .builder()
                .id(UUID.randomUUID())
                .sagaId(outboxMessage.getSagaId())
                .userId(outboxMessage.getUserId())
                .restaurantId(outboxMessage.getRestaurantId())
                .trackingId(outboxMessage.getTrackingId())
                .orderStatus(outboxMessage.getOrderStatus())
                .outboxStatus(outboxMessage.getOutboxStatus())
                .build();
    }

    public RestaurantApprovalOutboxMessage restaurantOutboxMessageEntityToMessage(RestaurantApprovalOutboxMessageEntity entity) {
        return RestaurantApprovalOutboxMessage
                .builder()
                .orderId(UUID.randomUUID())
                .sagaId(entity.getSagaId())
                .userId(entity.getUserId())
                .restaurantId(entity.getRestaurantId())
                .trackingId(entity.getTrackingId())
                .orderStatus(entity.getOrderStatus())
                .outboxStatus(entity.getOutboxStatus())
                .build();
    }


    public OrderApprovalOutboxMessage entityToOrderApprovalOutboxMessage(OrderApprovalOutboxMessageEntity entity) {
        return OrderApprovalOutboxMessage.builder()
                .id(entity.getId())
                .sagaId(entity.getSagaId())
                .orderId(entity.getOrderId())
                .userId(entity.getUserId())
                .restaurantId(entity.getRestaurantId())
                .orderStatus(entity.getOrderStatus())
                .outboxStatus(entity.getOutboxStatus())
                .version(entity.getVersion())
                .build();
    }

    public OrderApprovalOutboxMessageEntity outboxMessageToOrderApprovalOutboxMessageEntity(OrderApprovalOutboxMessage message) {
        return OrderApprovalOutboxMessageEntity.builder()
                .id(message.getId())
                .sagaId(message.getSagaId())
                .orderId(message.getOrderId())
                .userId(message.getUserId())
                .restaurantId(message.getRestaurantId())
                .orderStatus(message.getOrderStatus())
                .outboxStatus(message.getOutboxStatus())
                .version(message.getVersion())
                .build();
    }

}
