package com.example.stock.stockservice.application.mapper;

import com.example.stock.stockservice.application.ports.input.web.OrderStatusResponse;
import com.example.stock.common.command.StockBuyCommand;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import com.example.stock.stockservice.core.vo.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StockDataMapper {

    public StockBuyEvent stockBuyCommandToEvent(
            StockBuyCommand stockBuyCommand,
            UUID orderId
    ) {
        return StockBuyEvent
                .builder()
                .orderId(orderId)
                .productId(stockBuyCommand.productId())
                .userId(stockBuyCommand.userId())
                .quantity(stockBuyCommand.quantity())
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
