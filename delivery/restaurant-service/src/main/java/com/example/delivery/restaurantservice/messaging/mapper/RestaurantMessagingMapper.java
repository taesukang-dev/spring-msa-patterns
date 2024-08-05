package com.example.delivery.restaurantservice.messaging.mapper;

import com.example.delivery.infrastructure.model.RestaurantApprovalRequestAvroModel;
import com.example.delivery.infrastructure.vo.OrderStatus;
import com.example.delivery.restaurantservice.application.dto.RestaurantApprovalRequest;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMessagingMapper {

    public RestaurantApprovalRequest avroModelToRestaurantApprovalRequest(RestaurantApprovalRequestAvroModel avroModel) {
        return RestaurantApprovalRequest.builder()
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

}
