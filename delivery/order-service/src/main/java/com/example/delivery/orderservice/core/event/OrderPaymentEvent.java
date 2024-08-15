package com.example.delivery.orderservice.core.event;

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
@Getter
@Builder
public class OrderPaymentEvent {
    private UUID id;
    private UUID sagaId;
    private LocalDateTime createdAt;
    private OutboxStatus outboxStatus;
    private BigDecimal totalPrice;
    private Long userId;
    private Long version;
}
