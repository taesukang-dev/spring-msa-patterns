package com.example.stock.paymentservice.application.ports.input.message;

import com.example.stock.paymentservice.application.dto.PaymentRequest;

public interface PaymentRequestMessageListener {
    void completeOrder(PaymentRequest paymentRequest);
}
