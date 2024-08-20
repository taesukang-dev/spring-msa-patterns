package com.example.delivery.orderservice.application.ports.output;

import com.example.delivery.orderservice.application.outbox.OrderPaymentOutboxMessage;
import com.example.delivery.outbox.OutboxStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderPaymentOutboxMessageRepository {
    OrderPaymentOutboxMessage save(OrderPaymentOutboxMessage orderPaymentOutboxMessage);

    Optional<OrderPaymentOutboxMessage> findById(UUID id);

    Optional<List<OrderPaymentOutboxMessage>> findByStatus(OutboxStatus status);
}
