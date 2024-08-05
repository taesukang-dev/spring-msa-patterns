package com.example.delivery.orderservice.application;

import com.example.delivery.orderservice.application.ports.output.RestaurantApprovalExternalMessageListener;
import com.example.delivery.orderservice.application.ports.output.RestaurantApprovalMessagePublisher;
import com.example.delivery.orderservice.core.event.RestaurantApprovalEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class RestaurantApprovalExternalMessageListenerImpl implements RestaurantApprovalExternalMessageListener {
    private final RestaurantApprovalMessagePublisher restaurantApprovalMessagePublisher;

    @Override
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessageHandle(RestaurantApprovalEvent restaurantApprovalEvent) {
        restaurantApprovalMessagePublisher.publish(restaurantApprovalEvent);
    }
}
