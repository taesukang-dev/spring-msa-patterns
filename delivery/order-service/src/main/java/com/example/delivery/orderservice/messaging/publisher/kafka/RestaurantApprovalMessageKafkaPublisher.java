package com.example.delivery.orderservice.messaging.publisher.kafka;

import com.example.delivery.infrastructure.model.RestaurantApprovalRequestAvroModel;
import com.example.delivery.orderservice.application.ports.output.RestaurantApprovalMessagePublisher;
import com.example.delivery.orderservice.core.event.RestaurantApprovalEvent;
import com.example.delivery.orderservice.messaging.mapper.OrderMessagingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.example.delivery.infrastructure.kafka.KafkaConst.RESTAURANT_APPROVAL_TOPIC;

@RequiredArgsConstructor
@Component
public class RestaurantApprovalMessageKafkaPublisher implements RestaurantApprovalMessagePublisher {

    private final KafkaTemplate<String, RestaurantApprovalRequestAvroModel> kafkaTemplate;
    private final OrderMessagingMapper mapper;

    @Override
    public void publish(RestaurantApprovalEvent restaurantApprovalEvent) {
        kafkaTemplate.send(
                RESTAURANT_APPROVAL_TOPIC,
                mapper.eventToRestaurantApprovalRequestAvroModel(restaurantApprovalEvent)
        );
    }
}
