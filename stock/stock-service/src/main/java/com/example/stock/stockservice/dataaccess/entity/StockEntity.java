package com.example.stock.stockservice.dataaccess.entity;

import com.example.stock.stockservice.core.vo.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
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
}
