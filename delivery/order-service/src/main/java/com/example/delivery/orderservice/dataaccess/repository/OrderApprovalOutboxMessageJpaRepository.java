package com.example.delivery.orderservice.dataaccess.repository;

import com.example.delivery.orderservice.dataaccess.entity.outbox.OrderApprovalOutboxMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderApprovalOutboxMessageJpaRepository extends JpaRepository<OrderApprovalOutboxMessageEntity, UUID> {
}
