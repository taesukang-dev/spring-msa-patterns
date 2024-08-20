package com.example.delivery.orderservice.application;

import com.example.delivery.orderservice.application.outbox.OrderPaymentOutboxMessage;
import com.example.delivery.orderservice.application.outbox.RestaurantApprovalOutboxMessage;
import com.example.delivery.outbox.OutboxStatus;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Component
public class MessageCallbackHelper {

    public BiConsumer<SendResult<String, ?>, Throwable> applyCallback(
            OrderPaymentOutboxMessage message,
            Consumer<OrderPaymentOutboxMessage> save
    ) {
        return (result, ex) -> {
            if (ex == null) {
                save.accept(message.updateStatus(OutboxStatus.COMPLETED));
            } else {
                save.accept(message.updateStatus(OutboxStatus.COMPLETED));
            }
        };
    }

    public BiConsumer<SendResult<String, ?>, Throwable> applyCallback(
            RestaurantApprovalOutboxMessage message,
            Consumer<RestaurantApprovalOutboxMessage> save
    ) {
        return (result, ex) -> {
            if (ex == null) {
                save.accept(message.updateStatus(OutboxStatus.COMPLETED));
            } else {
                save.accept(message.updateStatus(OutboxStatus.COMPLETED));
            }
        };
    }


}
