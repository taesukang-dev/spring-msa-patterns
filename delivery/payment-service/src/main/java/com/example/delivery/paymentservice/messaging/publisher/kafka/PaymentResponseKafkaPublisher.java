package com.example.delivery.paymentservice.messaging.publisher.kafka;

import com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel;
import com.example.delivery.paymentservice.application.dto.PaymentResponse;
import com.example.delivery.paymentservice.application.ports.output.PaymentResponseMessagePublisher;
import com.example.delivery.paymentservice.messaging.mapper.PaymentMessagingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.example.delivery.infrastructure.kafka.KafkaConst.PAYMENT_RESPONSE_TOPIC;

@Slf4j
@RequiredArgsConstructor
@Component
public class PaymentResponseKafkaPublisher implements PaymentResponseMessagePublisher {

    private final KafkaTemplate<String, OrderPaymentResponseAvroModel> kafkaTemplate;
    private final PaymentMessagingMapper mapper;

    @Override
    public void send(PaymentResponse paymentResponse) {
        log.info("Payment Response Message Published {}", paymentResponse.getSagaId());

        kafkaTemplate.send(
                PAYMENT_RESPONSE_TOPIC,
                mapper.paymentResponseToAvroModel(paymentResponse)
        );
    }
}
