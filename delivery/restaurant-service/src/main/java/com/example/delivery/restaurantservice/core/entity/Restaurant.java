package com.example.delivery.restaurantservice.core.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class Restaurant {
    private UUID restaurantId;
    private List<Product> products;
    private boolean isActive;

    public boolean isAvailable() {
        return isActive && products.stream().filter(e -> !e.isAvailable()).toList().isEmpty();
    }
}
