package com.example.delivery.paymentservice.messaging.mapper;

import com.example.delivery.infrastructure.model.OrderPaymentAvroModel;
import com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel;
import com.example.delivery.outbox.OutboxStatus;
import com.example.delivery.paymentservice.application.dto.PaymentRequest;
import com.example.delivery.paymentservice.application.dto.PaymentResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class PaymentMessagingMapper {

    public PaymentRequest avroModelToPaymentRequest(OrderPaymentAvroModel avroModel) {
        return PaymentRequest.builder()
                .id(avroModel.getId())
                .orderId(avroModel.getOrderId())
                .sagaId(avroModel.getSagaId())
                .createdAt(LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(avroModel.getCreatedAt()),
                        ZoneId.of("Asia/Seoul")
                ))
                .outboxStatus(OutboxStatus.valueOf(avroModel.getOutboxStatus()))
                .totalPrice(avroModel.getTotalPrice())
                .userId(avroModel.getUserId())
                .build();
    }

    public OrderPaymentResponseAvroModel paymentResponseToAvroModel(PaymentResponse response) {
        return OrderPaymentResponseAvroModel.newBuilder()
                .setId(response.getId())
                .setOrderId(response.getOrderId())
                .setSagaId(response.getSagaId())
                .setCreatedAt(
                        response.getCreatedAt().atZone(ZoneId.of("Asia/Seoul")).toInstant().getEpochSecond()
                )
                .setOutboxStatus(response.getOutboxStatus().name())
                .setTotalPrice(response.getTotalPrice())
                .setUserId(response.getUserId())
                .setResult(response.isResult())
                .build();
    }
}
