package com.example.delivery.orderservice.dataaccess.repository;

import com.example.delivery.orderservice.dataaccess.entity.outbox.RestaurantApprovalOutboxMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantApprovalOutboxMessageJpaRepository extends JpaRepository<RestaurantApprovalOutboxMessageEntity, UUID> {
    Optional<RestaurantApprovalOutboxMessageEntity> findBySagaId(UUID sagaId);
}
