package com.example.stock.stockservice.application.ports.output;

import com.example.stock.stockservice.core.event.StockBuyEvent;

public interface OrderRequestMessagePublisher {
    void publish(StockBuyEvent stockBuyEvent);
}