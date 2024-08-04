package com.example.delivery.orderservice.dataaccess.adapter;

import com.example.delivery.orderservice.application.ports.output.RestaurantRepository;
import com.example.delivery.orderservice.core.entity.Restaurant;
import com.example.delivery.orderservice.dataaccess.mapper.RestaurantDataAccessMapper;
import com.example.delivery.orderservice.dataaccess.repository.RestaurantJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantDataAccessMapper mapper;

    @Override
    public Optional<Restaurant> findById(UUID restaurantId, List<UUID> productIds) {
        return restaurantJpaRepository.findByRestaurantIdAndProductIdIn(
                restaurantId, productIds
        ).map(mapper::restaurantEntityToRestaurant);
    }
}
