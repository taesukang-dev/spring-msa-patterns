package com.example.delivery.orderservice.application;

import com.example.delivery.orderservice.application.dto.PaymentResponse;
import com.example.delivery.orderservice.application.ports.input.PaymentResponseMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {

    @Override
    public void paymentApproved(PaymentResponse paymentResponse) {
        // outbox completed
        // restaurant -> approval request
    }

    @Override
    public void paymentRejected(PaymentResponse paymentResponse) {

    }
}
