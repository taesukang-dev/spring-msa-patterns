package com.example.delivery.restaurantservice.application.ports.input.web;

import java.util.UUID;

public record UpdateRestaurantCommand(
        UUID restaurantId,
        UUID productId
) {
}
