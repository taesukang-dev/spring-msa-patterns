package com.example.delivery.orderservice.application.ports.output;

import com.example.delivery.orderservice.application.outbox.OrderApprovalOutboxMessage;

public interface OrderApprovalOutboxMessageRepository {
    OrderApprovalOutboxMessage save(OrderApprovalOutboxMessage message);
}
