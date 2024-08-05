package com.example.delivery.orderservice.application.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record OrderCommand(
    Long userId,
    UUID restaurantId,
    BigDecimal totalPrice,
    List<OrderItemCommand> items,
    String deliveryAddress
) {
}
