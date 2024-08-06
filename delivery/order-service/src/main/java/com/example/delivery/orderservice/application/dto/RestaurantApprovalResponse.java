package com.example.delivery.orderservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RestaurantApprovalResponse {
    private UUID sagaId;
    private UUID orderId;
    private Long userId;
    private UUID restaurantId;
    private boolean result;
}
