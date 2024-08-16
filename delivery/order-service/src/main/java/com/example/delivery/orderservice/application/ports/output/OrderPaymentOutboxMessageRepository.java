package com.example.delivery.orderservice.application.ports.output;

import com.example.delivery.orderservice.application.outbox.OrderPaymentOutboxMessage;

import java.util.Optional;
import java.util.UUID;

public interface OrderPaymentOutboxMessageRepository {
    OrderPaymentOutboxMessage save(OrderPaymentOutboxMessage orderPaymentOutboxMessage);

    Optional<OrderPaymentOutboxMessage> findById(UUID id);
}
