package com.example.delivery.orderservice.application;

import com.example.delivery.infrastructure.vo.OrderStatus;
import com.example.delivery.orderservice.application.dto.RestaurantApprovalResponse;
import com.example.delivery.orderservice.application.mapper.OrderDataMapper;
import com.example.delivery.orderservice.application.outbox.OrderPaymentOutboxMessage;
import com.example.delivery.orderservice.application.outbox.RestaurantApprovalOutboxMessage;
import com.example.delivery.orderservice.application.ports.input.RestaurantApprovalResponseMessageListener;
import com.example.delivery.orderservice.application.ports.output.OrderApprovalOutboxMessageRepository;
import com.example.delivery.orderservice.application.ports.output.OrderPaymentOutboxMessageRepository;
import com.example.delivery.orderservice.application.ports.output.OrderRepository;
import com.example.delivery.orderservice.application.ports.output.RestaurantApprovalOutboxMessageRepository;
import com.example.delivery.orderservice.core.entity.Order;
import com.example.delivery.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprovalResponseMessageListener {
    private final SagaHelper sagaHelper;

    @Override
    public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {
        sagaHelper.completeOrder(restaurantApprovalResponse);
    }

    // TODO : Scheduler -> Failed counting??

    @Override
    public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {

    }
}
