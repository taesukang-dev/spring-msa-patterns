package com.example.delivery.restaurantservice.messaging.listner.kafka;

import com.example.delivery.infrastructure.model.RestaurantApprovalRequestAvroModel;
import com.example.delivery.restaurantservice.application.ports.input.RestaurantApprovalRequestMessageListener;
import com.example.delivery.restaurantservice.messaging.mapper.RestaurantMessagingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.example.delivery.infrastructure.kafka.KafkaConst.RESTAURANT_APPROVAL_TOPIC;

@RequiredArgsConstructor
@Component
public class RestaurantApprovalRequestKafkaListener {

    private final RestaurantApprovalRequestMessageListener restaurantApprovalRequestMessageListener;
    private final RestaurantMessagingMapper mapper;

    @KafkaListener(topics = RESTAURANT_APPROVAL_TOPIC, groupId = "spring")
    public void consumer(@Payload RestaurantApprovalRequestAvroModel restaurantApprovalRequestAvroModel) {
        restaurantApprovalRequestMessageListener.approveOrder(
                mapper.avroModelToRestaurantApprovalRequest(
                        restaurantApprovalRequestAvroModel
                )
        );
    }
}
