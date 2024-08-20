package com.example.delivery.orderservice.dataaccess.entity.outbox;

import com.example.delivery.outbox.OutboxStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "payment_outbox_message")
@Entity
public class OrderPaymentOutboxMessageEntity {
    @Id
    private UUID id;
    private UUID sagaId;
    private UUID orderId;
    @Enumerated(EnumType.STRING)
    private OutboxStatus outboxStatus;
    private BigDecimal totalPrice;
    @CreatedDate
    private LocalDateTime createdAt;
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPaymentOutboxMessageEntity that = (OrderPaymentOutboxMessageEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
