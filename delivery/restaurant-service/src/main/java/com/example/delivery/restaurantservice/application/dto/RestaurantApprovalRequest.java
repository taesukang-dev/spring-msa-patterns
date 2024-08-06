package com.example.delivery.restaurantservice.application.dto;

import com.example.delivery.infrastructure.vo.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RestaurantApprovalRequest {
    private UUID sagaId;
    private UUID orderId;
    private Long userId;
    private UUID restaurantId;
    private String deliveryAddress;
    private BigDecimal totalPrice;
    private List<UUID> productIds;
    private UUID trackingId;
    private OrderStatus orderStatus;
}
