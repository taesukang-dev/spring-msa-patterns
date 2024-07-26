package com.example.stock.paymentservice.messaging.publisher.kafka;

import com.example.stock.common.infrastructure.model.PaymentResponseAvroModel;
import com.example.stock.paymentservice.application.dto.PaymentResponse;
import com.example.stock.paymentservice.application.ports.outpub.PaymentResponseMessagePublisher;
import com.example.stock.paymentservice.messaging.mapper.PaymentMessagingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.example.stock.common.infrastructure.kafka.KafkaConst.PAYMENT_TOPIC;

@RequiredArgsConstructor
@Component
public class PaymentResponseKafkaPublisher implements PaymentResponseMessagePublisher {

    private final KafkaTemplate<String, PaymentResponseAvroModel> kafkaTemplate;
    private final PaymentMessagingMapper mapper;

    @Override
    public void send(PaymentResponse paymentResponse) {
        kafkaTemplate.send(
                PAYMENT_TOPIC,
                mapper.paymentResponseToAvroModel(paymentResponse)
        );
    }
}
