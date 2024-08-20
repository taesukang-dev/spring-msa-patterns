package com.example.delivery.orderservice.dataaccess.entity.outbox;

import com.example.delivery.infrastructure.vo.OrderStatus;
import com.example.delivery.outbox.OutboxStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "order_approval_outbox_message")
@Entity
public class OrderApprovalOutboxMessageEntity {
    @Id
    private UUID id;
    private UUID sagaId;
    private UUID orderId;
    private Long userId;
    private UUID restaurantId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private OutboxStatus outboxStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderApprovalOutboxMessageEntity that = (OrderApprovalOutboxMessageEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
