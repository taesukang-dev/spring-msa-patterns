package com.example.delivery.orderservice.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
@IdClass(OrderItemEntityId.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_item")
@Entity
public class OrderItemEntity {
    @Id
    private Long orderItemId;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @Setter
    private OrderEntity order;
    private UUID productId;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal subTotal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemEntity that = (OrderItemEntity) o;
        return Objects.equals(orderItemId, that.orderItemId) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItemId, order);
    }
}
