package com.example.stock.stockservice.core.outbox;

import com.example.stock.common.infrastructure.outbox.OutboxStatus;
import com.example.stock.stockservice.core.vo.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class OrderOutboxMessage {
    private Long id;
    private Long orderId;
    private Long userId;
    private UUID productId;
    private int quantity;
    private OrderStatus orderStatus;
    private OutboxStatus outboxStatus;
}
