package com.example.delivery.orderservice.application.ports.output;

import com.example.delivery.orderservice.core.entity.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(UUID orderId);
}
