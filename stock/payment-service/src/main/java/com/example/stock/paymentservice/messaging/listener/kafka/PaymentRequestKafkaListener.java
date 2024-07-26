package com.example.stock.paymentservice.messaging.listener.kafka;

import com.example.stock.common.infrastructure.kafka.KafkaConst;
import com.example.stock.common.infrastructure.model.OrderAvroModel;
import com.example.stock.paymentservice.application.ports.input.message.PaymentRequestMessageListener;
import com.example.stock.paymentservice.messaging.mapper.PaymentMessagingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentRequestKafkaListener {
    private final PaymentRequestMessageListener paymentRequestMessageListener;
    private final PaymentMessagingMapper mapper;

    @KafkaListener(topics = KafkaConst.ORDER_TOPIC, groupId = "spring")
    public void consumer(@Payload OrderAvroModel orderAvroModel) {
        paymentRequestMessageListener.completeOrder(
                mapper.orderAvroModelToPaymentRequest(orderAvroModel)
        );
    }
}
