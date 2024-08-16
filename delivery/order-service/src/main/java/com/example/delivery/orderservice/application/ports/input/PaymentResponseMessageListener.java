package com.example.delivery.orderservice.application.ports.input;

import com.example.delivery.orderservice.application.dto.PaymentResponse;
import com.example.delivery.orderservice.application.dto.RestaurantApprovalResponse;

public interface PaymentResponseMessageListener {
    void paymentApproved(PaymentResponse paymentResponse);

    void paymentRejected(PaymentResponse paymentResponse);
}
