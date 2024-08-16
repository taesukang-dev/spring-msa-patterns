package com.example.delivery.paymentservice.application.ports.output;

import com.example.delivery.paymentservice.application.dto.PaymentResponse;

public interface PaymentResponseMessagePublisher {
    void send(PaymentResponse paymentResponse);
}
