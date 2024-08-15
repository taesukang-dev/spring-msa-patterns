package com.example.delivery.orderservice.application.ports.output;

import com.example.delivery.orderservice.application.outbox.RestaurantApprovalOutboxMessage;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantApprovalOutboxMessageRepository {
    RestaurantApprovalOutboxMessage save(RestaurantApprovalOutboxMessage restaurantApprovalOutboxMessage);

    Optional<RestaurantApprovalOutboxMessage> findBySagaId(UUID sagaId);
}
