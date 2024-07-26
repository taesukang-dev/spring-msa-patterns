package com.example.stock.stockservice.dataaccess.repository;

import com.example.stock.stockservice.dataaccess.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockJpaRepository extends JpaRepository<StockEntity, UUID> {
}
