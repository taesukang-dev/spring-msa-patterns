package com.example.delivery.orderservice.dataaccess.entity.outbox;

import com.example.delivery.outbox.OutboxStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment_outbox")
@Entity
public class OrderPaymentOutboxMessageEntity {
    @Id
    private UUID id;
    private UUID sagaId;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private OutboxStatus outboxStatus;
    private BigDecimal totalPrice;
    private Long userId;
    @Version
    private Long version;

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
