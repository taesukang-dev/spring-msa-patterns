package com.example.delivery.orderservice.application;

import com.example.delivery.orderservice.application.dto.PaymentResponse;
import com.example.delivery.orderservice.application.ports.input.PaymentResponseMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {

    private final SagaHelper sagaHelper;

    @Override
    public void paymentApproved(PaymentResponse paymentResponse) {
        sagaHelper.completePayment(paymentResponse);
    }

    @Override
    public void paymentRejected(PaymentResponse paymentResponse) {
        sagaHelper.cancelPayment(paymentResponse);
    }

    // TODO : Scheduler -> Failed counting??
}
