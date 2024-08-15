package com.example.delivery.orderservice.messaging.publisher.kafka;

import com.example.delivery.infrastructure.model.OrderPaymentAvroModel;
import com.example.delivery.orderservice.application.ports.output.OrderPaymentMessagePublisher;
import com.example.delivery.orderservice.core.event.OrderPaymentEvent;
import com.example.delivery.orderservice.messaging.mapper.OrderMessagingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.example.delivery.infrastructure.kafka.KafkaConst.PAYMENT_TOPIC;

@RequiredArgsConstructor
@Component
public class OrderPaymentMessageKafkaPublisher implements OrderPaymentMessagePublisher {

    private final KafkaTemplate<String, OrderPaymentAvroModel> kafkaTemplate;
    private final OrderMessagingMapper mapper;

    @Override
    public void publish(OrderPaymentEvent orderPaymentEvent) {
        kafkaTemplate.send(
                PAYMENT_TOPIC,
                mapper.eventToAvroModel(orderPaymentEvent)
        );
    }
}
