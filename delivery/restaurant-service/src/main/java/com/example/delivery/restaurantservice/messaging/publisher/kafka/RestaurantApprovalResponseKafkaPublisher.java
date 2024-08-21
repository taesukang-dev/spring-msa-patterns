package com.example.delivery.restaurantservice.messaging.publisher.kafka;

import com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel;
import com.example.delivery.restaurantservice.application.ports.output.RestaurantApprovalResponseMessagePublisher;
import com.example.delivery.restaurantservice.core.event.RestaurantApprovalResponseEvent;
import com.example.delivery.restaurantservice.messaging.mapper.RestaurantMessagingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.example.delivery.infrastructure.kafka.KafkaConst.RESTAURANT_APPROVAL_RESPONSE_TOPIC;

@Slf4j
@RequiredArgsConstructor
@Component
public class RestaurantApprovalResponseKafkaPublisher implements RestaurantApprovalResponseMessagePublisher {

    private final KafkaTemplate<String, RestaurantApprovalResponseAvroModel> kafkaTemplate;
    private final RestaurantMessagingMapper mapper;

    @Override
    public void publish(RestaurantApprovalResponseEvent event) {
        log.info("Restaurant Approval Response Message Published {}", event.getSagaId());

        kafkaTemplate.send(
                RESTAURANT_APPROVAL_RESPONSE_TOPIC,
                mapper.eventToRestaurantApprovalResponseAvroModel(event)
        );
    }
}
