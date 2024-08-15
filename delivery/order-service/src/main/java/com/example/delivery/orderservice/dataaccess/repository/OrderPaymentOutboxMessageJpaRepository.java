package com.example.delivery.orderservice.dataaccess.repository;

import com.example.delivery.orderservice.dataaccess.entity.outbox.OrderPaymentOutboxMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderPaymentOutboxMessageJpaRepository extends JpaRepository<OrderPaymentOutboxMessageEntity, UUID> {

}
