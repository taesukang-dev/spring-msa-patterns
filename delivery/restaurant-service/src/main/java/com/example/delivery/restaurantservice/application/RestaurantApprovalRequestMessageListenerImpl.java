package com.example.delivery.restaurantservice.application;

import com.example.delivery.infrastructure.vo.OrderStatus;
import com.example.delivery.restaurantservice.application.dto.RestaurantApprovalRequest;
import com.example.delivery.restaurantservice.application.ports.input.RestaurantApprovalRequestMessageListener;
import com.example.delivery.restaurantservice.application.ports.output.RestaurantRepository;
import com.example.delivery.restaurantservice.core.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.delivery.infrastructure.vo.OrderStatus.PENDING;

@RequiredArgsConstructor
@Component
public class RestaurantApprovalRequestMessageListenerImpl implements RestaurantApprovalRequestMessageListener {

    private final RestaurantRepository restaurantRepository;

    @Override
    public void approveOrder(RestaurantApprovalRequest restaurantApprovalRequest) {
        if (restaurantApprovalRequest.getOrderStatus().equals(PENDING)) {
            throw new RuntimeException("Not Paid Yet");
        }

        Restaurant restaurant = restaurantRepository.findById(
                restaurantApprovalRequest.getRestaurantId(),
                restaurantApprovalRequest.getProductIds()
        ).orElseThrow(() -> new RuntimeException("Restaurant Not Found"));

        if (restaurant.isAvailable()) {
            // TODO : Approve -> Kafka Messaging
        } else {
            // TODO : Cannot Approve -> Kafka Messaging
        }

        // TODO : Outbox
    }
}
