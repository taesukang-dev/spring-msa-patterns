package com.example.delivery.orderservice.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Product {
    private UUID productId;
    private String name;
    private BigDecimal price;

    public Product(UUID productId) {
        this.productId = productId;
    }
}
