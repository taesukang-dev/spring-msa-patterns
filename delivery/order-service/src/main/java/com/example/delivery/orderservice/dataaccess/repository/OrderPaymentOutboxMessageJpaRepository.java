package com.example.delivery.orderservice.dataaccess.repository;

import com.example.delivery.orderservice.dataaccess.entity.outbox.OrderPaymentOutboxMessageEntity;
import com.example.delivery.outbox.OutboxStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderPaymentOutboxMessageJpaRepository extends JpaRepository<OrderPaymentOutboxMessageEntity, UUID> {

    Optional<List<OrderPaymentOutboxMessageEntity>> findByOutboxStatus(OutboxStatus status);
}
