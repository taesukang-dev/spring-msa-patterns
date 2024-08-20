package com.example.delivery.restaurantservice.application.ports.input.web;

import com.example.delivery.restaurantservice.application.ports.input.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/update")
    public void update(@RequestBody UpdateRestaurantCommand command) {
        restaurantService.updateProductUnAvailable(
                command.restaurantId(),
                command.productId()
        );
    }
}
