package com.example.delivery.restaurantservice.messaging.listner.kafka;

import com.example.delivery.infrastructure.model.RestaurantApprovalRequestAvroModel;
import com.example.delivery.restaurantservice.application.ports.input.RestaurantApprovalRequestMessageListener;
import com.example.delivery.restaurantservice.messaging.mapper.RestaurantMessagingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.example.delivery.infrastructure.kafka.KafkaConst.RESTAURANT_APPROVAL_TOPIC;

@Slf4j
@RequiredArgsConstructor
@Component
public class RestaurantApprovalRequestKafkaListener {

    private final RestaurantApprovalRequestMessageListener restaurantApprovalRequestMessageListener;
    private final RestaurantMessagingMapper mapper;

    @KafkaListener(topics = RESTAURANT_APPROVAL_TOPIC, groupId = "spring")
    public void consumer(@Payload RestaurantApprovalRequestAvroModel restaurantApprovalRequestAvroModel) {
        log.info("Restaurant Approval Request Message Consumed Saga : {}", restaurantApprovalRequestAvroModel.getSagaId());

        try {
            restaurantApprovalRequestMessageListener.approveOrder(
                    mapper.avroModelToRestaurantApprovalRequest(
                            restaurantApprovalRequestAvroModel
                    )
            );
        // The specified error or error code in practice
        } catch (RuntimeException e) {
            log.error("Error Occurred {}", e.getMessage());
        }
    }
}
