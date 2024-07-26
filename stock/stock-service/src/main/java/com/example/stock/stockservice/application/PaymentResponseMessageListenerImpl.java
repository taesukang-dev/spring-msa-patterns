package com.example.stock.stockservice.application;

import com.example.stock.stockservice.application.dto.PaymentResponse;
import com.example.stock.stockservice.application.ports.input.message.PaymentResponseMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {

    private final OrderPaymentSaga orderPaymentSaga;

    @Override
    public void paymentComplete(PaymentResponse paymentResponse) {
        orderPaymentSaga.process(paymentResponse);
    }

    @Override
    public void paymentCancelled(PaymentResponse paymentResponse) {
        orderPaymentSaga.rollback(paymentResponse);
    }
}
