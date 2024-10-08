package com.example.delivery.paymentservice.messaging.listener.kafka;

import com.example.delivery.infrastructure.kafka.KafkaConst;
import com.example.delivery.infrastructure.model.OrderPaymentAvroModel;
import com.example.delivery.paymentservice.application.ports.input.PaymentMessageListener;
import com.example.delivery.paymentservice.messaging.mapper.PaymentMessagingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.example.delivery.infrastructure.kafka.KafkaConst.PAYMENT_TOPIC;

@Slf4j
@RequiredArgsConstructor
@Component
public class PaymentKafkaListener {

    private final PaymentMessageListener paymentMessageListener;
    private final PaymentMessagingMapper mapper;

    @KafkaListener(topics = PAYMENT_TOPIC, groupId = "spring")
    public void consumer(@Payload OrderPaymentAvroModel avroModel) {
        log.info("Payment Request Message Consumed Saga : {}", avroModel.getSagaId());

        paymentMessageListener.requestPayment(
                mapper.avroModelToPaymentRequest(avroModel)
        );
    }
}
