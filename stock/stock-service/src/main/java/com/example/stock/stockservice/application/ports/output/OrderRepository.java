package com.example.stock.stockservice.application.ports.output;

import com.example.stock.stockservice.core.Order;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findById(Long id);
}
