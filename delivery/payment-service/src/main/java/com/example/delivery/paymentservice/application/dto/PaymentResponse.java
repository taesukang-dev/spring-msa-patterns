package com.example.delivery.paymentservice.application.dto;

import com.example.delivery.outbox.OutboxStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class PaymentResponse {
    private UUID id;
    private UUID orderId;
    private UUID sagaId;
    private LocalDateTime createdAt;
    private OutboxStatus outboxStatus;
    private BigDecimal totalPrice;
    private Long userId;
    private boolean result;
}
