package com.example.stock.paymentservice.messaging.mapper;

import com.example.stock.common.infrastructure.model.OrderAvroModel;
import com.example.stock.common.infrastructure.model.PaymentResponseAvroModel;
import com.example.stock.paymentservice.application.dto.PaymentRequest;
import com.example.stock.paymentservice.application.dto.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class PaymentMessagingMapper {

    public PaymentRequest orderAvroModelToPaymentRequest(OrderAvroModel orderAvroModel) {
        return PaymentRequest.builder()
                .productId(orderAvroModel.getProductId())
                .orderId(orderAvroModel.getOrderId())
                .userId(orderAvroModel.getUserId())
                .quantity(orderAvroModel.getQuantity())
                .build();
    }

    public PaymentResponseAvroModel paymentResponseToAvroModel(PaymentResponse paymentResponse) {
        return PaymentResponseAvroModel.newBuilder()
                .setProductId(paymentResponse.getProductId())
                .setOrderId(paymentResponse.getOrderId())
                .setUserId(paymentResponse.getUserId())
                .setPaymentId(paymentResponse.getPaymentId())
                .setQuantity(paymentResponse.getQuantity())
                .setResult(paymentResponse.isResult())
                .build();

    }
}
