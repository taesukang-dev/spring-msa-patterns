package com.example.delivery.orderservice.dataaccess.repository.restaurant;

import com.example.delivery.orderservice.dataaccess.entity.restaurant.RestaurantEntity;
import com.example.delivery.orderservice.dataaccess.entity.restaurant.RestaurantEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, RestaurantEntityId> {
    Optional<List<RestaurantEntity>> findByRestaurantIdAndProductIdIn(UUID restaurantId, List<UUID> productIds);
}