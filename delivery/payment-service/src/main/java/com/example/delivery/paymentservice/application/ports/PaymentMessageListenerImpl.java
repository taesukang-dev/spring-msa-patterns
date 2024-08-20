package com.example.delivery.paymentservice.application.ports;

import com.example.delivery.paymentservice.application.dto.PaymentRequest;
import com.example.delivery.paymentservice.application.dto.PaymentResponse;
import com.example.delivery.paymentservice.application.ports.input.PaymentMessageListener;
import com.example.delivery.paymentservice.application.ports.output.PaymentResponseMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentMessageListenerImpl implements PaymentMessageListener {

    private final PaymentResponseMessagePublisher publisher;

    @Override
    public void requestPayment(PaymentRequest paymentRequest) {
        // PG Request...
        // Save Payment Result to Database...
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}

        publisher.send(
                PaymentResponse.builder()
                        .id(paymentRequest.getId())
                        .orderId(paymentRequest.getOrderId())
                        .sagaId(paymentRequest.getSagaId())
                        .createdAt(paymentRequest.getCreatedAt())
                        .outboxStatus(paymentRequest.getOutboxStatus())
                        .totalPrice(paymentRequest.getTotalPrice())
                        .userId(paymentRequest.getUserId())
                        .result(true)
                        .build()
        );
    }
}
