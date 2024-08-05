package com.example.delivery.restaurantservice.dataaccess.repository;

import com.example.delivery.restaurantservice.dataaccess.entity.RestaurantEntity;
import com.example.delivery.restaurantservice.dataaccess.entity.RestaurantEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, RestaurantEntityId> {
    Optional<List<RestaurantEntity>> findByRestaurantIdAndProductIdIn(UUID restaurantId, List<UUID> productIds);
}