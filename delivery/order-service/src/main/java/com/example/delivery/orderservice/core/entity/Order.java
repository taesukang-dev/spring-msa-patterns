package com.example.delivery.orderservice.core.entity;

import com.example.delivery.orderservice.core.vo.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Builder
public class Order {
    private UUID id;
    private Long userId;
    private final UUID restaurantId;
    private final String deliveryAddress;
    private final BigDecimal totalPrice;
    private final List<OrderItem> orderItems;
    private UUID trackingId;
    private OrderStatus orderStatus;

    public void initOrder() {
        AtomicInteger ai = new AtomicInteger(1);
        this.id = UUID.randomUUID();
        this.trackingId = UUID.randomUUID();
        this.orderStatus = OrderStatus.PENDING;
        orderItems.forEach(orderItem -> orderItem.initialOrderItem(this.id, ai.getAndIncrement()));
    }

    public void validateOrder() {
        if (orderStatus != null || id != null) {
            throw new RuntimeException("Already Initialized");
        }

        if (totalPrice == null || totalPrice.compareTo(BigDecimal.ZERO) > 0) {
            throw new RuntimeException("Not Available to buy");
        }

        BigDecimal itemsTotal = orderItems.stream().map(item -> {
            if (!item.isPriceValid()) throw new RuntimeException("Not Available to buy");
            return item.getSubTotal();
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
        if (!totalPrice.equals(itemsTotal)) {
            throw new RuntimeException("Total and SubTotal are not equal");
        }
    }

    public void pay() {
        if (orderStatus != OrderStatus.PENDING) {
            throw new RuntimeException("Order is not in correct state for operation");
        }
        orderStatus = OrderStatus.PAID;
    }

    public void approve() {
        if (orderStatus != OrderStatus.PAID) {
            throw new RuntimeException("Order is not in correct state for operation");
        }
        orderStatus = OrderStatus.APPROVED;
    }

    public void cancel() {
        if (orderStatus != OrderStatus.PAID) {
            throw new RuntimeException("Order is not in correct state for operation");
        }
        orderStatus = OrderStatus.CANCELLED;
    }
}
