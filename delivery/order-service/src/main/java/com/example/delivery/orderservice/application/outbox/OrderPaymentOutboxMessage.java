package com.example.delivery.orderservice.application.outbox;

import com.example.delivery.outbox.OutboxStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class OrderPaymentOutboxMessage {
    private UUID id;
    private UUID sagaId;
    private LocalDateTime createdAt;
    private OutboxStatus outboxStatus;
    private BigDecimal totalPrice;
    private Long userId;
    private Long version;
}
