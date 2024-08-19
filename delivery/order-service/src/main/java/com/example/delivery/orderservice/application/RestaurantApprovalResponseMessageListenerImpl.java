package com.example.delivery.orderservice.application;

import com.example.delivery.orderservice.application.dto.RestaurantApprovalResponse;
import com.example.delivery.orderservice.application.ports.input.RestaurantApprovalResponseMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprovalResponseMessageListener {
    private final SagaHelper sagaHelper;

    @Override
    public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {
        sagaHelper.completeOrder(restaurantApprovalResponse);
    }

    @Override
    public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {
        sagaHelper.cancelOrder(restaurantApprovalResponse);
    }

    // TODO : Scheduler -> Failed counting??
}
