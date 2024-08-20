package com.example.delivery.orderservice.application.ports.output;

import com.example.delivery.orderservice.application.outbox.RestaurantApprovalOutboxMessage;
import com.example.delivery.outbox.OutboxStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantApprovalOutboxMessageRepository {
    RestaurantApprovalOutboxMessage save(RestaurantApprovalOutboxMessage restaurantApprovalOutboxMessage);

    Optional<List<RestaurantApprovalOutboxMessage>> findByStatus(OutboxStatus status);

    Optional<RestaurantApprovalOutboxMessage> findBySagaId(UUID sagaId);
}
