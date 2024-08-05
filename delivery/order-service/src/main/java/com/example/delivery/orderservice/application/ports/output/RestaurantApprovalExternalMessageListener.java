package com.example.delivery.orderservice.application.ports.output;

import com.example.delivery.orderservice.core.event.RestaurantApprovalEvent;

public interface RestaurantApprovalExternalMessageListener {
    void sendMessageHandle(RestaurantApprovalEvent restaurantApprovalEvent);
}
