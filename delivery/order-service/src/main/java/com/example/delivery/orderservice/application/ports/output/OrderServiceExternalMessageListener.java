package com.example.delivery.orderservice.application.ports.output;

import com.example.delivery.orderservice.core.event.CancelOrderEvent;
import com.example.delivery.orderservice.core.event.OrderPaymentEvent;
import com.example.delivery.orderservice.core.event.PaymentCompensateEvent;
import com.example.delivery.orderservice.core.event.RestaurantApprovalEvent;

public interface OrderServiceExternalMessageListener {
    void sendMessageHandle(RestaurantApprovalEvent restaurantApprovalEvent);

    void sendMessageHandle(OrderPaymentEvent orderPaymentEvent);

    void sendMessageHandle(PaymentCompensateEvent event);

    void sendMessageHandle(CancelOrderEvent event);
}
