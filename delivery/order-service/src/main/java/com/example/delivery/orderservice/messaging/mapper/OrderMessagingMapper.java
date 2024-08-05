package com.example.delivery.orderservice.messaging.mapper;

import com.example.delivery.infrastructure.model.RestaurantApprovalRequestAvroModel;
import com.example.delivery.infrastructure.model.RestaurantOrderStatus;
import com.example.delivery.orderservice.core.event.RestaurantApprovalEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderMessagingMapper {

    public RestaurantApprovalRequestAvroModel eventToRestaurantApprovalRequestAvroModel(RestaurantApprovalEvent event) {
        return RestaurantApprovalRequestAvroModel
                .newBuilder()
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
}
