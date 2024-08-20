package com.example.delivery.restaurantservice.application.ports.input;

import java.util.UUID;

public interface RestaurantService {
    void updateProductUnAvailable(UUID restaurantId, UUID productId);
}
