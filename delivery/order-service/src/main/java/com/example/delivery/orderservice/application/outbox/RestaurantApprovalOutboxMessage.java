package com.example.delivery.orderservice.application.outbox;

import com.example.delivery.infrastructure.vo.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.delivery.outbox.OutboxStatus;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantApprovalOutboxMessage {
    private UUID id;
    private UUID sagaId;
    private UUID orderId;
    private Long userId;
    private UUID restaurantId;
    private UUID trackingId;
    private OrderStatus orderStatus;
    private OutboxStatus outboxStatus;

    public RestaurantApprovalOutboxMessage updateStatus(OutboxStatus outboxStatus) {
        this.outboxStatus = outboxStatus;
        return this;
    }
}
