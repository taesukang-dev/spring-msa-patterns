package com.example.delivery.restaurantservice.application.ports.input;

import com.example.delivery.restaurantservice.application.dto.RestaurantApprovalRequest;

public interface RestaurantApprovalRequestMessageListener {
    void approveOrder(RestaurantApprovalRequest restaurantApprovalRequest);
}
