package com.example.stock.stockservice.application;

import com.example.stock.stockservice.application.ports.input.StockService;
import com.example.stock.stockservice.application.ports.input.web.OrderStatusCommand;
import com.example.stock.stockservice.application.ports.input.web.OrderStatusResponse;
import com.example.stock.stockservice.application.ports.input.web.StockBuyCommand;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.Stock;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StockServiceImpl implements StockService {

    private final StockServiceHelper stockServiceHelper;
    private final OrderCommandHandler orderCommandHandler;

    @Override
    public Stock save(Stock stock) {
        return stockServiceHelper.save(stock);
    }

    @Override
    public Order buy(StockBuyCommand command) {
        // TODO : Kafka Messaging
        return stockServiceHelper.buy(
                new StockBuyEvent(
                        command.productId(),
                        command.quantity()
                )
        );
    }

    @Override
    public OrderStatusResponse getOrderStatus(OrderStatusCommand orderStatusCommand) {
        return orderCommandHandler.getOrderStatus(orderStatusCommand);
    }
}
