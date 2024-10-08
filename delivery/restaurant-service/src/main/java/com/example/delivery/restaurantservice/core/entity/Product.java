package com.example.delivery.restaurantservice.core.entity;

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
    private boolean isAvailable;

    public Product(UUID productId) {
        this.productId = productId;
    }

    public void markAsUnavailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
