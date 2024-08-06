package com.example.delivery.orderservice.messaging.mapper;

import com.example.delivery.infrastructure.model.RestaurantApprovalRequestAvroModel;
import com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel;
import com.example.delivery.infrastructure.model.RestaurantOrderStatus;
import com.example.delivery.orderservice.application.dto.RestaurantApprovalResponse;
import com.example.delivery.orderservice.core.event.RestaurantApprovalEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderMessagingMapper {

    public RestaurantApprovalRequestAvroModel eventToRestaurantApprovalRequestAvroModel(RestaurantApprovalEvent event) {
        return RestaurantApprovalRequestAvroModel.newBuilder()
                .setSagaId(event.getSagaId())
                .setOrderId(event.getOrderId())
                .setUserId(event.getUserId())
                .setRestaurantId(event.getRestaurantId())
                .setDeliveryAddress(event.getDeliveryAddress())
                .setTotalPrice(event.getTotalPrice())
                .setProductIds(event.getProductIds())
                .setTrackingId(event.getTrackingId())
                .setOrderStatus(
                        RestaurantOrderStatus
                                .valueOf(event.getOrderStatus().toString())
                )
                .build();
    }

    public RestaurantApprovalResponse avroModelToRestaurantApprovalResponse(RestaurantApprovalResponseAvroModel avroModel) {
        return RestaurantApprovalResponse.builder()
                .sagaId(avroModel.getSagaId())
                .orderId(avroModel.getOrderId())
                .userId(avroModel.getUserId())
                .restaurantId(avroModel.getRestaurantId())
                .result(avroModel.getResult())
                .build();
    }
}
