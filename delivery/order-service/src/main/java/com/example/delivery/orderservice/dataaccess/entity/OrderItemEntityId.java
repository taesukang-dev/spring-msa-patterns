package com.example.delivery.orderservice.dataaccess.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntityId implements Serializable {

    private Long orderItemId;
    private OrderEntity order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemEntityId that = (OrderItemEntityId) o;
        return Objects.equals(orderItemId, that.orderItemId) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItemId, order);
    }
}
