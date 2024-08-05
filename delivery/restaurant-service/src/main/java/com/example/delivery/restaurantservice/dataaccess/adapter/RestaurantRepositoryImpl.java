package com.example.delivery.restaurantservice.dataaccess.adapter;

import com.example.delivery.restaurantservice.application.ports.output.RestaurantRepository;
import com.example.delivery.restaurantservice.core.entity.Restaurant;
import com.example.delivery.restaurantservice.dataaccess.mapper.RestaurantDataAccessMapper;
import com.example.delivery.restaurantservice.dataaccess.repository.RestaurantJpaRepository;
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
