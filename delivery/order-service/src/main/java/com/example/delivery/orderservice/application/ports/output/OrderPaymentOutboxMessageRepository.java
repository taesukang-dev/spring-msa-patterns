package com.example.delivery.orderservice.application.ports.output;

import com.example.delivery.orderservice.application.outbox.OrderPaymentOutboxMessage;

public interface OrderPaymentOutboxMessageRepository {
    OrderPaymentOutboxMessage save(OrderPaymentOutboxMessage orderPaymentOutboxMessage);
}
