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
    public void order(OrderCommand orderCommand) {
        sagaHelper.startOrder(orderCommand);
    }

    // TODO : Scheduler -> Failed counting??

    @Override
    public void cancel() {

    }
}
