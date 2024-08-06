package com.example.delivery.restaurantservice.application.ports.output;

import com.example.delivery.restaurantservice.core.event.RestaurantApprovalResponseEvent;

public interface RestaurantApprovalResponseMessagePublisher {
    void publish(RestaurantApprovalResponseEvent event);
}
