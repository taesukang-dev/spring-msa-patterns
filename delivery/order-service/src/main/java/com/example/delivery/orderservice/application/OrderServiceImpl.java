package com.example.delivery.orderservice.application;

import com.example.delivery.orderservice.application.ports.input.OrderService;
import com.example.delivery.orderservice.application.dto.OrderCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final SagaHelper sagaHelper;

    @Override
    // user -> order-service request
    public void order(OrderCommand orderCommand) {
        sagaHelper.startOrder(orderCommand);
    }

    @Override
    public void cancel() {

    }
}
