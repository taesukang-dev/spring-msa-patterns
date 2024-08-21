package com.example.delivery.orderservice.messaging.publisher.kafka;

import com.example.delivery.infrastructure.model.RestaurantApprovalRequestAvroModel;
import com.example.delivery.orderservice.application.ports.output.RestaurantApprovalMessagePublisher;
import com.example.delivery.orderservice.core.event.RestaurantApprovalEvent;
import com.example.delivery.orderservice.messaging.mapper.OrderMessagingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static com.example.delivery.infrastructure.kafka.KafkaConst.RESTAURANT_APPROVAL_TOPIC;

@Slf4j
@RequiredArgsConstructor
@Component
public class RestaurantApprovalMessageKafkaPublisher implements RestaurantApprovalMessagePublisher {

    private final KafkaTemplate<String, RestaurantApprovalRequestAvroModel> kafkaTemplate;
    private final OrderMessagingMapper mapper;

    @Override
    public void publish(RestaurantApprovalEvent restaurantApprovalEvent) {
        log.info("Restaurant Approval Request Message Published {}", restaurantApprovalEvent.getSagaId());

        CompletableFuture<SendResult<String, RestaurantApprovalRequestAvroModel>> result = kafkaTemplate.send(
                RESTAURANT_APPROVAL_TOPIC,
                mapper.eventToRestaurantApprovalRequestAvroModel(restaurantApprovalEvent)
        );
        result.whenComplete(restaurantApprovalEvent.getCallback());
    }
}
