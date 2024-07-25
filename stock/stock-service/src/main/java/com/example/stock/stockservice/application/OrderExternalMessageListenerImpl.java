package com.example.stock.stockservice.application;

import com.example.stock.stockservice.application.ports.output.OrderExternalMessageListener;
import com.example.stock.stockservice.application.ports.output.OrderRequestMessagePublisher;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class OrderExternalMessageListenerImpl implements OrderExternalMessageListener {

    private final OrderRequestMessagePublisher orderRequestMessagePublisher;

    @Override
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessageHandle(StockBuyEvent stockBuyEvent) {
        orderRequestMessagePublisher.publish(stockBuyEvent);
    }
}
