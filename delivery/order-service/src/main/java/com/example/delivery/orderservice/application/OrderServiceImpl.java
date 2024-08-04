package com.example.delivery.orderservice.application;

import com.example.delivery.orderservice.application.mapper.OrderDataMapper;
import com.example.delivery.orderservice.application.ports.input.OrderService;
import com.example.delivery.orderservice.application.dto.OrderCommand;
import com.example.delivery.orderservice.core.entity.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDataMapper mapper;

    @Override
    public void order(OrderCommand orderCommand) {
        Order order = mapper.orderCommandToOrder(orderCommand);
        order.validateOrder();
        order.initOrder();
        // 1. 주문이 들어오면 restaurant 에서 available 한지 check
        // 2. 결제 진행
    }

    @Override
    public void cancel() {

    }
}
