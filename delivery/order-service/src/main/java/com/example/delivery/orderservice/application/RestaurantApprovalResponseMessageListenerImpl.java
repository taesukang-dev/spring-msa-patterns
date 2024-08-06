package com.example.delivery.orderservice.application;

import com.example.delivery.orderservice.application.dto.RestaurantApprovalResponse;
import com.example.delivery.orderservice.application.ports.input.RestaurantApprovalResponseMessageListener;

public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprovalResponseMessageListener {

    @Override
    public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {
        // TODO : restaurant approval -> Outbox Complete
        // TODO : order approval -> outbox started

        // TODO : Order 진행
        // TODO : 1. pay
        // TODO : 2. 주문
    }

    @Override
    public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {

    }
}
