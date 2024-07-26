package com.example.stock.paymentservice.application.ports.outpub;

import com.example.stock.paymentservice.application.dto.PaymentResponse;

public interface PaymentResponseMessagePublisher {
    void send(PaymentResponse paymentResponse);
}
