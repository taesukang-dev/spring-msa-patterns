package com.example.delivery.paymentservice.application.ports.input;

import com.example.delivery.paymentservice.application.dto.PaymentRequest;

public interface PaymentMessageListener {
    void requestPayment(PaymentRequest paymentRequest);
}
