package com.example.delivery.paymentservice.application.dto;

import com.example.delivery.outbox.OutboxStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PaymentRequest {
    private UUID id;
    private UUID orderId;
    private UUID sagaId;
    private LocalDateTime createdAt;
    private OutboxStatus outboxStatus;
    private BigDecimal totalPrice;
    private Long userId;
}
