package com.example.delivery.orderservice.application;

import com.example.delivery.orderservice.application.ports.output.OrderPaymentMessagePublisher;
import com.example.delivery.orderservice.application.ports.output.OrderServiceExternalMessageListener;
import com.example.delivery.orderservice.application.ports.output.RestaurantApprovalMessagePublisher;
import com.example.delivery.orderservice.core.event.CancelOrderEvent;
import com.example.delivery.orderservice.core.event.OrderPaymentEvent;
import com.example.delivery.orderservice.core.event.PaymentCompensateEvent;
import com.example.delivery.orderservice.core.event.RestaurantApprovalEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class OrderServiceExternalMessageListenerImpl implements OrderServiceExternalMessageListener {
    private final RestaurantApprovalMessagePublisher restaurantApprovalMessagePublisher;
    private final OrderPaymentMessagePublisher orderPaymentMessagePublisher;

    @Override
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessageHandle(OrderPaymentEvent orderPaymentEvent) {
        orderPaymentMessagePublisher.publish(orderPaymentEvent);
    }

    @Override
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessageHandle(PaymentCompensateEvent event) {
        // Cancel Payment Request To Payment Service
    }

    @Override
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessageHandle(CancelOrderEvent event) {
        // Cancel Payment Request To Payment Service
    }

    @Override
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessageHandle(RestaurantApprovalEvent restaurantApprovalEvent) {
        restaurantApprovalMessagePublisher.publish(restaurantApprovalEvent);
    }
}
