package com.example.delivery.restaurantservice.application;

import com.example.delivery.restaurantservice.application.ports.output.RestaurantApprovalResponseExternalMessageListener;
import com.example.delivery.restaurantservice.application.ports.output.RestaurantApprovalResponseMessagePublisher;
import com.example.delivery.restaurantservice.core.event.RestaurantApprovalResponseEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class RestaurantApprovalResponseExternalMessageListenerImpl implements RestaurantApprovalResponseExternalMessageListener {

    private final RestaurantApprovalResponseMessagePublisher publisher;

    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessageHandle(RestaurantApprovalResponseEvent event) {
        publisher.publish(event);
    }
}
