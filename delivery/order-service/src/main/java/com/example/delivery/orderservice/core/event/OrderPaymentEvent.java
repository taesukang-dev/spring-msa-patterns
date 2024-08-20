package com.example.delivery.orderservice.core.event;

import com.example.delivery.outbox.OutboxStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.kafka.support.SendResult;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.BiConsumer;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class OrderPaymentEvent {
    private UUID id;
    private UUID orderId;
    private UUID sagaId;
    private LocalDateTime createdAt;
    private OutboxStatus outboxStatus;
    private BigDecimal totalPrice;
    private Long userId;
    private BiConsumer<SendResult<String, ?>, Throwable> callback;
}
