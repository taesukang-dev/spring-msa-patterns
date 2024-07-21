package com.example.stock.stockservice.dataaccess.mapper;

import com.example.stock.stockservice.core.Stock;
import com.example.stock.stockservice.dataaccess.entity.StockEntity;
import org.springframework.stereotype.Component;

@Component
public class StockDataAccessMapper {

    public Stock stockEntityToStock(StockEntity stockEntity) {
        return Stock.builder()
                .productId(stockEntity.getProductId())
                .brandId(stockEntity.getBrandId())
                .price(stockEntity.getPrice())
                .totalQuantity(stockEntity.getTotalQuantity())
                .availableQuantity(stockEntity.getAvailableQuantity())
                .orderStatus(stockEntity.getOrderStatus())
                .build();
    }

    public StockEntity stockToStockEntity(Stock stock) {
        return StockEntity.builder()
                .productId(stock.getProductId())
                .brandId(stock.getBrandId())
                .price(stock.getPrice())
                .totalQuantity(stock.getTotalQuantity())
                .availableQuantity(stock.getAvailableQuantity())
                .orderStatus(stock.getOrderStatus())
                .build();
    }

}
