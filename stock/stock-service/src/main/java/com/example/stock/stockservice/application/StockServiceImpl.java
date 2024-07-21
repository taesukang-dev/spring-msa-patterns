package com.example.stock.stockservice.application;

import com.example.stock.stockservice.application.ports.input.StockService;
import com.example.stock.stockservice.application.ports.output.StockRepository;
import com.example.stock.stockservice.core.Stock;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public boolean buy(StockBuyEvent stockBuyEvent) {
        Stock stock = stockRepository.findById(stockBuyEvent.getProductId())
                .orElseThrow(() -> new RuntimeException("Item Not Found"));
        if (!stock.isAvailableToBuy(stockBuyEvent.getQuantity())) {
            throw new RuntimeException("Not available to buy");
        }

        // TODO : Move To Helper
        return stockRepository.decreaseQuantity(
                stockBuyEvent.getProductId(),
                stockBuyEvent.getQuantity()
        );
    }
}
