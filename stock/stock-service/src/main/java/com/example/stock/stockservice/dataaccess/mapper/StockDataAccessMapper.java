package com.example.stock.stockservice.dataaccess.mapper;

import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.Stock;
import com.example.stock.stockservice.dataaccess.entity.OrderEntity;
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
                .build();
    }

    public StockEntity stockToStockEntity(Stock stock) {
        return StockEntity.builder()
                .productId(stock.getProductId())
                .brandId(stock.getBrandId())
                .price(stock.getPrice())
                .totalQuantity(stock.getTotalQuantity())
                .availableQuantity(stock.getAvailableQuantity())
                .build();
    }

    public Order orderEntityToOrder(OrderEntity order) {
        return Order.builder()
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    public OrderEntity orderToOrderEntity(Order order) {
        return OrderEntity
                .builder()
                .id(order.getId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .orderStatus(order.getOrderStatus())
                .build();
    }

}
