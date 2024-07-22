package com.example.stock.stockservice.application.ports.mapper;

import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import com.example.stock.stockservice.core.vo.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class StockDataMapper {

    public Order stockBuyEventToOrder(StockBuyEvent stockBuyEvent) {
        return Order.builder()
                .productId(stockBuyEvent.getProductId())
                .quantity(stockBuyEvent.getQuantity())
                .orderStatus(OrderStatus.PENDING)
                .build();
    }

}
