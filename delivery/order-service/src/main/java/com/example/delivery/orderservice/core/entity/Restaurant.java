package com.example.delivery.orderservice.core.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class Restaurant {

    private UUID restaurantId;
    private List<Product> products;
    private boolean isAvailable;

    public boolean isAvailable() {
        return isAvailable && products.stream().filter(e -> !e.isAvailable()).toList().isEmpty();
    }
}
