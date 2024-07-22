package com.example.stock.stockservice.application;

import com.example.stock.stockservice.application.ports.input.StockService;
import com.example.stock.stockservice.application.ports.mapper.StockDataMapper;
import com.example.stock.stockservice.application.ports.output.OrderRepository;
import com.example.stock.stockservice.application.ports.output.StockRepository;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.Stock;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final OrderRepository orderRepository;
    private final StockDataMapper mapper;

    @Override
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
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
            // TODO : Out of stock response
        }
        return orderRepository.save(
                mapper.stockBuyEventToOrder(stockBuyEvent)
        );
    }
}
