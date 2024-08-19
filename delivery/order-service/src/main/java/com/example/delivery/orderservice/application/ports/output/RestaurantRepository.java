package com.example.delivery.orderservice.application.ports.output;

import com.example.delivery.orderservice.core.entity.Restaurant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantRepository {
    Optional<Restaurant> findById(
            UUID restaurantId,
            List<UUID> productIds
    );
}
