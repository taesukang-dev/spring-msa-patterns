package com.example.stock.paymentservice.application;

import com.example.stock.paymentservice.application.dto.PaymentRequest;
import com.example.stock.paymentservice.application.dto.PaymentResponse;
import com.example.stock.paymentservice.application.ports.input.message.PaymentRequestMessageListener;
import com.example.stock.paymentservice.application.ports.outpub.PaymentResponseMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@RequiredArgsConstructor
@Service
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {

    private final PaymentResponseMessagePublisher publisher;

    @Override
    public void completeOrder(PaymentRequest paymentRequest) {
        // PG Request...
        // Save Payment Result to Database...
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}

        // response payment result
        publisher.send(new PaymentResponse(
                paymentRequest.getProductId(),
                paymentRequest.getOrderId(),
                paymentRequest.getUserId(),
                new SecureRandom().nextLong(),
                paymentRequest.getQuantity(),
                true
        ));
    }
}
