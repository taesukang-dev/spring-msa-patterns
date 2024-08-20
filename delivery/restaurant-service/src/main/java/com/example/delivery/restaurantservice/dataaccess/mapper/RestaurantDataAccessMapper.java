package com.example.delivery.restaurantservice.dataaccess.mapper;

import com.example.delivery.restaurantservice.core.entity.Product;
import com.example.delivery.restaurantservice.core.entity.Restaurant;
import com.example.delivery.restaurantservice.dataaccess.entity.RestaurantEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantDataAccessMapper {

    public Restaurant restaurantEntityToRestaurant(List<RestaurantEntity> restaurantEntities) {
        RestaurantEntity restaurantEntity =
                restaurantEntities.stream().findFirst().orElseThrow(() ->
                        new RuntimeException("Restaurant Not Found"));

        List<Product> restaurantProducts = restaurantEntities.stream().map(entity ->
                new Product(entity.getProductId(), entity.getProductName(),
                        entity.getProductPrice(), entity.getProductAvailable())
        ).toList();

        return Restaurant.builder()
                .restaurantId(restaurantEntity.getRestaurantId())
                .products(restaurantProducts)
                .isAvailable(restaurantEntity.getRestaurantActive())
                .build();
    }

    public List<RestaurantEntity> restaurantToRestaurantEntity(Restaurant restaurant) {
        return restaurant.getProducts().stream()
                .map(product -> RestaurantEntity.builder()
                        .restaurantId(restaurant.getRestaurantId())
                        .productId(product.getProductId())
                        .restaurantActive(restaurant.isAvailable())
                        .productName(product.getName())
                        .productPrice(product.getPrice())
                        .productAvailable(product.isAvailable())
                        .build())
                .toList();
    }

}
