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
public class StockEntity {
    @Id
    private UUID productId;
    private String brandId;
    private int price;
    private int totalQuantity;
    private int availableQuantity;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Version
    private Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockEntity that = (StockEntity) o;
        return Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
