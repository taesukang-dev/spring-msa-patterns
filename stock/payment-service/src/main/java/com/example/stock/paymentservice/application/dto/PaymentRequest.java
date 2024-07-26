package com.example.stock.paymentservice.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class PaymentRequest {
    private UUID productId;
    private UUID orderId;
    private Long userId;
    private int quantity;
}
