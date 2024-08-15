package com.example.delivery.orderservice.application.ports.output;

import com.example.delivery.orderservice.core.event.OrderPaymentEvent;

public interface OrderPaymentMessagePublisher {
    void publish(OrderPaymentEvent orderPaymentEvent);
}
