package com.example.delivery.orderservice.messaging.listener.kafka;

import com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel;
import com.example.delivery.orderservice.application.dto.PaymentResponse;
import com.example.delivery.orderservice.application.ports.input.PaymentResponseMessageListener;
import com.example.delivery.orderservice.messaging.mapper.OrderMessagingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.example.delivery.infrastructure.kafka.KafkaConst.PAYMENT_RESPONSE_TOPIC;

@RequiredArgsConstructor
@Component
public class PaymentResponseKafkaListener {

    private final OrderMessagingMapper mapper;
    private final PaymentResponseMessageListener messageListener;

    @KafkaListener(topics = PAYMENT_RESPONSE_TOPIC)
    public void consumer(@Payload OrderPaymentResponseAvroModel paymentResponseAvroModel) {
        PaymentResponse response = mapper.avroModelToPaymentResponse(paymentResponseAvroModel);
        if (response.isResult()) {
            messageListener.paymentApproved(response);
        } else {
            messageListener.paymentRejected(response);
        }
    }

}
