package com.example.stock.stockservice.dataaccess.repository;

import com.example.stock.stockservice.dataaccess.entity.OrderOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderOutBoxJpaRepository extends JpaRepository<OrderOutboxEntity, Long> {
    Optional<OrderOutboxEntity> findByOrderId(UUID id);
}
