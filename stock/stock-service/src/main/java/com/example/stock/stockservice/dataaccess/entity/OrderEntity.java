package com.example.stock.stockservice.dataaccess.entity;

import com.example.stock.stockservice.core.vo.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity {

    @Id
    private UUID id;
    private Long userId;
    private UUID productId;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Version
    private Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
