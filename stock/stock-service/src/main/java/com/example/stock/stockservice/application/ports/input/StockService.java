package com.example.stock.stockservice.application.ports.input;

import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.Stock;
import com.example.stock.stockservice.core.event.StockBuyEvent;

public interface StockService {
    Stock save(Stock stock);
    Order buy(StockBuyEvent stockBuyEvent);
}