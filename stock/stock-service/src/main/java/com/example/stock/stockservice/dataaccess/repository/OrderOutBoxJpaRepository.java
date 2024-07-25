package com.example.stock.stockservice.dataaccess.repository;

import com.example.stock.stockservice.dataaccess.entity.OrderOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderOutBoxJpaRepository extends JpaRepository<OrderOutboxEntity, Long> {
}
