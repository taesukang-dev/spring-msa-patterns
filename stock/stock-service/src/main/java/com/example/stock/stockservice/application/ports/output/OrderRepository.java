package com.example.stock.stockservice.application.ports.output;

import com.example.stock.stockservice.core.Order;

public interface OrderRepository {
    Order save(Order order);
}
