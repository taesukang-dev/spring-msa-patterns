package com.example.stock.stockservice.messaging.mapper;

import com.example.stock.common.infrastructure.model.OrderAvroModel;
import com.example.stock.common.infrastructure.model.PaymentResponseAvroModel;
import com.example.stock.stockservice.application.dto.PaymentResponse;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderMessagingMapper {

    public OrderAvroModel stockBuyEventToOrderAvroModel(StockBuyEvent stockBuyEvent) {
        return OrderAvroModel.newBuilder()
                .setOrderId(stockBuyEvent.getOrderId())
                .setProductId(stockBuyEvent.getProductId())
                .setUserId(stockBuyEvent.getUserId())
                .setQuantity(stockBuyEvent.getQuantity())
                .build();
    }

    public PaymentResponse paymentResponseAvroModelToPayResponse(PaymentResponseAvroModel paymentResponseAvroModel) {
        return PaymentResponse.builder()
                .productId(paymentResponseAvroModel.getProductId())
                .orderId(paymentResponseAvroModel.getOrderId())
                .userId(paymentResponseAvroModel.getUserId())
                .paymentId(paymentResponseAvroModel.getPaymentId())
                .quantity(paymentResponseAvroModel.getQuantity())
                .build();
    }
}
