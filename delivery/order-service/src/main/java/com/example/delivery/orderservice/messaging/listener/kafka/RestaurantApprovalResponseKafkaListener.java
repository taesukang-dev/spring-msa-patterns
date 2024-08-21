package com.example.delivery.orderservice.messaging.listener.kafka;

import com.example.delivery.infrastructure.kafka.KafkaConst;
import com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel;
import com.example.delivery.orderservice.application.dto.RestaurantApprovalResponse;
import com.example.delivery.orderservice.application.ports.input.RestaurantApprovalResponseMessageListener;
import com.example.delivery.orderservice.messaging.mapper.OrderMessagingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RestaurantApprovalResponseKafkaListener {

    private final RestaurantApprovalResponseMessageListener messageListener;
    private final OrderMessagingMapper mapper;

    @KafkaListener(topics = KafkaConst.RESTAURANT_APPROVAL_RESPONSE_TOPIC, groupId = "spring")
    public void consumer(@Payload RestaurantApprovalResponseAvroModel responseAvroModel) {
        log.info("Restaurant Approval Response Message Consumed Saga : {}", responseAvroModel.getSagaId());

        boolean approved = responseAvroModel.getResult();
        RestaurantApprovalResponse response
                = mapper.avroModelToRestaurantApprovalResponse(responseAvroModel);

        if (approved) {
            messageListener.orderApproved(response);
        } else {
            messageListener.orderRejected(response);
        }

    }
}
