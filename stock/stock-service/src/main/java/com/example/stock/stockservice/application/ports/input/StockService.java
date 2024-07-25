package com.example.stock.stockservice.application.ports.input;

import com.example.stock.common.command.StockBuyCommand;
import com.example.stock.stockservice.application.ports.input.web.OrderStatusResponse;
import com.example.stock.stockservice.application.ports.input.web.OrderStatusCommand;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.Stock;

public interface StockService {
    Stock save(Stock stock);
    Order buy(StockBuyCommand stockBuyEvent);

    OrderStatusResponse getOrderStatus(OrderStatusCommand orderStatusCommand);
}
