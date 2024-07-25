package com.example.stock.stockservice.application;

import com.example.stock.stockservice.application.ports.input.web.OrderStatusCommand;
import com.example.stock.stockservice.application.ports.input.web.OrderStatusResponse;
import com.example.stock.stockservice.application.mapper.StockDataMapper;
import com.example.stock.stockservice.application.ports.output.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderCommandHandler {
    private final StockDataMapper mapper;
    private final OrderRepository orderRepository;

    public OrderStatusResponse getOrderStatus(OrderStatusCommand orderStatusCommand) {
        return orderRepository.findById(orderStatusCommand.orderId())
                .map(mapper::orderToOrderStatusResponse)
                .orElseThrow(() -> new RuntimeException("Order Not Found"));
    }
}
