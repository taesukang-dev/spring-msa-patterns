package com.example.delivery.restaurantservice.application;

import com.example.delivery.restaurantservice.application.ports.input.RestaurantService;
import com.example.delivery.restaurantservice.application.ports.output.RestaurantRepository;
import com.example.delivery.restaurantservice.core.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    @Override
    public void updateProductUnAvailable(UUID restaurantId, UUID productId) {
        Restaurant restaurant = repository.findById(
                restaurantId,
                List.of(productId)
        ).orElseThrow(() -> new RuntimeException("Restaurant Not Found"));

        restaurant.markProductAsUnavailable(productId);

        repository.save(restaurant);
    }
}
