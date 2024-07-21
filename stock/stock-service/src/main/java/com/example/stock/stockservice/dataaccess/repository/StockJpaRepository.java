package com.example.stock.stockservice.dataaccess.repository;

import com.example.stock.stockservice.dataaccess.entity.StockEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface StockJpaRepository extends JpaRepository<StockEntity, UUID> {
    @Modifying
    @Query("UPDATE StockEntity s " +
            "SET s.availableQuantity = s.availableQuantity - :quantity " +
            "where s.availableQuantity - :quantity >= 0 " +
            "and s.availableQuantity = :productId")
    int decreaseQuantity(@Param("productId") UUID productId, @Param("quantity") int quantity);
}
