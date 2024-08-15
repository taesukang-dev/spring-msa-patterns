package com.example.delivery.orderservice.messaging.mapper;

import com.example.delivery.infrastructure.model.OrderPaymentAvroModel;
import com.example.delivery.infrastructure.model.RestaurantApprovalRequestAvroModel;
import com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel;
import com.example.delivery.infrastructure.model.RestaurantOrderStatus;
import com.example.delivery.infrastructure.vo.OrderStatus;
import com.example.delivery.orderservice.application.dto.RestaurantApprovalResponse;
import com.example.delivery.orderservice.core.event.OrderPaymentEvent;
import com.example.delivery.orderservice.core.event.RestaurantApprovalEvent;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZoneOffset;

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
                .orderStatus(
                        OrderStatus.valueOf(
                                avroModel.getOrderStatus().toString()
                        )
                )
                .result(avroModel.getResult())
                .build();
    }

    public OrderPaymentAvroModel eventToAvroModel(OrderPaymentEvent event) {
        return OrderPaymentAvroModel.newBuilder()
                .setId(event.getId())
                .setSagaId(event.getSagaId())
                .setCreatedAt(
                        event.getCreatedAt().atZone(ZoneId.of("Asia/Seoul")).toInstant().getEpochSecond()
                )
                .setOutboxStatus(event.getOutboxStatus().name())
                .setTotalPrice(event.getTotalPrice())
                .setUserId(event.getUserId())
                .setVersion(event.getVersion())
                .build();
    }
}
