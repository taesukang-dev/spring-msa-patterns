package com.example.delivery.orderservice.application.outbox;

import com.example.delivery.infrastructure.vo.OrderStatus;
import com.example.delivery.outbox.OutboxStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
// TODO : Not In Use
public class OrderApprovalOutboxMessage {
    private UUID id;
    private UUID sagaId;
    private UUID orderId;
    private Long userId;
    private UUID restaurantId;
    private OrderStatus orderStatus;
    private OutboxStatus outboxStatus;

    public OrderApprovalOutboxMessage updateStatus(OutboxStatus outboxStatus) {
        this.outboxStatus = outboxStatus;
        return this;
    }
}
