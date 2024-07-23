package com.example.stock.stockservice.application.ports.mapper;

import com.example.stock.stockservice.application.ports.input.web.OrderStatusResponse;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import com.example.stock.stockservice.core.vo.OrderStatus;
import com.example.stock.stockservice.dataaccess.entity.OrderEntity;
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

    public OrderStatusResponse orderToOrderStatusResponse(Order order) {
       return OrderStatusResponse.builder()
               .id(order.getId())
               .productId(order.getProductId())
               .quantity(order.getQuantity())
               .orderStatus(order.getOrderStatus())
               .build();
    }

}
