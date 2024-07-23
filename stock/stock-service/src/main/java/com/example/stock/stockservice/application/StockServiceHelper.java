package com.example.stock.stockservice.application;

import com.example.stock.stockservice.application.ports.mapper.StockDataMapper;
import com.example.stock.stockservice.application.ports.output.OrderRepository;
import com.example.stock.stockservice.application.ports.output.StockRepository;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.Stock;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StockServiceHelper {

    private final OrderCommandHandler orderCommandHandler;
    private final StockRepository stockRepository;
    private final OrderRepository orderRepository;
    private final StockDataMapper mapper;

    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    // TODO : Distributed Locking
    public Order buy(StockBuyEvent stockBuyEvent) {
        Stock stock = stockRepository.findById(stockBuyEvent.getProductId())
                .orElseThrow(() -> new RuntimeException("Item Not Found"));
        if (!stock.isAvailableToBuy(stockBuyEvent.getQuantity())) {
            throw new RuntimeException("Not available to buy");
        }

        // TODO : Move To Helper -> and convert domain to response
        boolean updated = stockRepository.decreaseQuantity(
                stockBuyEvent.getProductId(),
                stockBuyEvent.getQuantity()
        );
        if (!updated) {
            throw new RuntimeException("Out of Stock");
        }
        return orderRepository.save(
                mapper.stockBuyEventToOrder(stockBuyEvent)
        );
    }

}
