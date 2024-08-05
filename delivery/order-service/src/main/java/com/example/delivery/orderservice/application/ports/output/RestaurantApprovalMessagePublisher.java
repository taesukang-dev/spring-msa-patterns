package com.example.delivery.orderservice.application.ports.output;

import com.example.delivery.orderservice.core.event.RestaurantApprovalEvent;

public interface RestaurantApprovalMessagePublisher {
    void publish(RestaurantApprovalEvent restaurantApprovalEvent);
}
