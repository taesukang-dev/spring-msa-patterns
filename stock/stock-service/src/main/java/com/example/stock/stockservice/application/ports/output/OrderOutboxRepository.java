package com.example.stock.stockservice.application.ports.output;

import com.example.stock.stockservice.core.outbox.OrderOutboxMessage;

public interface OrderOutboxRepository {
    OrderOutboxMessage save(OrderOutboxMessage orderOutboxMessage);
}
