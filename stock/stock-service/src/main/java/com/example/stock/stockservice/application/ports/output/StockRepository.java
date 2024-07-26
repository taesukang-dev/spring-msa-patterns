package com.example.stock.stockservice.application.ports.output;

import com.example.stock.stockservice.core.Stock;

import java.util.Optional;
import java.util.UUID;

public interface StockRepository {
    Optional<Stock> findById(UUID productId);

    Stock save(Stock stock);
}
