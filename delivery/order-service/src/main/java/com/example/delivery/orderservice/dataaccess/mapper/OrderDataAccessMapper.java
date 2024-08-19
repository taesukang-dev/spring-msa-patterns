package com.example.delivery.orderservice.dataaccess.mapper;

import com.example.delivery.orderservice.application.outbox.OrderApprovalOutboxMessage;
import com.example.delivery.orderservice.application.outbox.OrderPaymentOutboxMessage;
import com.example.delivery.orderservice.application.outbox.RestaurantApprovalOutboxMessage;
import com.example.delivery.orderservice.core.entity.Order;
import com.example.delivery.orderservice.core.entity.OrderItem;
import com.example.delivery.orderservice.core.entity.Product;
import com.example.delivery.orderservice.core.entity.Restaurant;
import com.example.delivery.orderservice.core.event.OrderPaymentEvent;
import com.example.delivery.orderservice.dataaccess.entity.OrderEntity;
import com.example.delivery.orderservice.dataaccess.entity.OrderItemEntity;
import com.example.delivery.orderservice.dataaccess.entity.outbox.OrderApprovalOutboxMessageEntity;
import com.example.delivery.orderservice.dataaccess.entity.outbox.OrderPaymentOutboxMessageEntity;
import com.example.delivery.orderservice.dataaccess.entity.outbox.RestaurantApprovalOutboxMessageEntity;
import com.example.delivery.orderservice.dataaccess.entity.restaurant.RestaurantEntity;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public OrderPaymentOutboxMessageEntity messageToOrderPaymentOutboxMessageEntity(OrderPaymentOutboxMessage message) {
        return OrderPaymentOutboxMessageEntity.builder()
                .id(message.getId())
                .sagaId(message.getSagaId())
                .createdAt(message.getCreatedAt())
                .outboxStatus(message.getOutboxStatus())
                .totalPrice(message.getTotalPrice())
                .userId(message.getUserId())
                .version(message.getVersion())
                .build();
    }

    public OrderPaymentOutboxMessage entityToOrderPaymentOutboxMessage(OrderPaymentOutboxMessageEntity entity) {
        return OrderPaymentOutboxMessage.builder()
                .id(entity.getId())
                .sagaId(entity.getSagaId())
                .createdAt(entity.getCreatedAt())
                .outboxStatus(entity.getOutboxStatus())
                .totalPrice(entity.getTotalPrice())
                .userId(entity.getUserId())
                .version(entity.getVersion())
                .build();
    }

    public Restaurant restaurantEntityToRestaurant(List<RestaurantEntity> restaurantEntities) {
        RestaurantEntity restaurantEntity =
                restaurantEntities.stream().findFirst().orElseThrow(() ->
                        new RuntimeException("Restaurant Not Found"));

        List<Product> restaurantProducts = restaurantEntities.stream().map(entity ->
                new Product(entity.getProductId(), entity.getProductName(),
                        entity.getProductPrice(), entity.getProductAvailable())
        ).toList();

        return Restaurant.builder()
                .restaurantId(restaurantEntity.getRestaurantId())
                .products(restaurantProducts)
                .isAvailable(restaurantEntity.getRestaurantActive())
                .build();
    }
}
