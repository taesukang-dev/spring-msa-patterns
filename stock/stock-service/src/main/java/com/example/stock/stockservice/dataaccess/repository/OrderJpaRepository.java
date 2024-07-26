package com.example.stock.stockservice.dataaccess.repository;

import com.example.stock.stockservice.dataaccess.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
}
