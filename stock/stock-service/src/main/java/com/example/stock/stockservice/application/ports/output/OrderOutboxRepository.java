package com.example.stock.stockservice.application.ports.output;

import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.outbox.OrderOutboxMessage;

import java.util.Optional;
import java.util.UUID;

public interface OrderOutboxRepository {
    OrderOutboxMessage save(OrderOutboxMessage orderOutboxMessage);

    Optional<OrderOutboxMessage> findByOrderId(UUID id);
}
