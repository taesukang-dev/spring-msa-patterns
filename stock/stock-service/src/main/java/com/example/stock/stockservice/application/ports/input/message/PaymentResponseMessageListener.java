package com.example.stock.stockservice.application.ports.input.message;

import com.example.stock.stockservice.application.dto.PaymentResponse;

public interface PaymentResponseMessageListener {
    void paymentComplete(PaymentResponse paymentResponse);

    void paymentCancelled(PaymentResponse paymentResponse);
}
