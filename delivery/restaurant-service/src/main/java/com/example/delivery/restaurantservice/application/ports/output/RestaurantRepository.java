package com.example.delivery.restaurantservice.application.ports.output;

import com.example.delivery.restaurantservice.core.entity.Restaurant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantRepository {
    Optional<Restaurant> findById(UUID restaurantId, List<UUID> productIds);
}
