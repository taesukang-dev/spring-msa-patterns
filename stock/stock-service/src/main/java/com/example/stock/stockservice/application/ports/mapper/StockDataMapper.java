package com.example.stock.stockservice.application.ports.mapper;

import com.example.stock.stockservice.application.ports.input.web.OrderStatusResponse;
import com.example.coupon.common.command.StockBuyCommand;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.vo.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class StockDataMapper {

    public Order stockBuyCommandToOrder(StockBuyCommand stockBuyEvent) {
        return Order.builder()
                .productId(stockBuyEvent.productId())
                .quantity(stockBuyEvent.quantity())
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
