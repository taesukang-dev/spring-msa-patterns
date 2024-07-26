package com.example.stock.stockservice.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class PaymentResponse {
    UUID productId;
    UUID orderId;
    Long userId;
    Long paymentId;
    int quantity;
}
