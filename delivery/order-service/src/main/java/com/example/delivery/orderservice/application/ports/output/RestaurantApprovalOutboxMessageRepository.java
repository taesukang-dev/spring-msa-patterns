package com.example.delivery.orderservice.application.ports.output;

import com.example.delivery.orderservice.application.outbox.RestaurantApprovalOutboxMessage;

public interface RestaurantApprovalOutboxMessageRepository {
    RestaurantApprovalOutboxMessage save(RestaurantApprovalOutboxMessage restaurantApprovalOutboxMessage);
}
