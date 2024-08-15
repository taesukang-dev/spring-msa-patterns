package com.example.delivery.restaurantservice.messaging.mapper;

import com.example.delivery.infrastructure.model.RestaurantApprovalRequestAvroModel;
import com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel;
import com.example.delivery.infrastructure.model.RestaurantOrderStatus;
import com.example.delivery.infrastructure.vo.OrderStatus;
import com.example.delivery.restaurantservice.application.dto.RestaurantApprovalRequest;
import com.example.delivery.restaurantservice.core.event.RestaurantApprovalResponseEvent;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMessagingMapper {

    public RestaurantApprovalRequest avroModelToRestaurantApprovalRequest(RestaurantApprovalRequestAvroModel avroModel) {
        return RestaurantApprovalRequest.builder()
                .sagaId(avroModel.getSagaId())
                .orderId(avroModel.getOrderId())
                .userId(avroModel.getUserId())
                .restaurantId(avroModel.getRestaurantId())
                .totalPrice(avroModel.getTotalPrice())
                .productIds(avroModel.getProductIds())
                .trackingId(avroModel.getTrackingId())
                .orderStatus(
                        OrderStatus.valueOf(
                                avroModel.getOrderStatus().toString()
                        )
                )
                .build();
    }

    public RestaurantApprovalResponseAvroModel eventToRestaurantApprovalResponseAvroModel(RestaurantApprovalResponseEvent event) {
        return RestaurantApprovalResponseAvroModel.newBuilder()
                .setSagaId(event.getSagaId())
                .setOrderId(event.getOrderId())
                .setUserId(event.getUserId())
                .setRestaurantId(event.getRestaurantId())
                .setOrderStatus(
                        RestaurantOrderStatus.valueOf(
                                event.getOrderStatus().toString()
                        )
                )
                .setResult(event.isResult())
                .build();
    }

}
