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
    private boolean isAvailable;

    public boolean checkAvailable() {
        return isAvailable && products.stream().filter(e -> !e.isAvailable()).toList().isEmpty();
    }

    public Restaurant markProductAsUnavailable(UUID productId) {
        Product product = products.stream()
                .filter(data -> data.getProductId().equals(productId)).findFirst()
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
        product.markAsUnavailable(false);
        return this;
    }
}
