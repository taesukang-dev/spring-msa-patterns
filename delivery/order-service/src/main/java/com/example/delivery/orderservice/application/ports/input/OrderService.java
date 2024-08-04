package com.example.delivery.orderservice.application.ports.input;

import com.example.delivery.orderservice.application.dto.OrderCommand;

public interface OrderService {
    // parameter should be orderCommand
    void order(OrderCommand orderCommand);

    // parameter should be orderCancelCommand
    void cancel();
}
