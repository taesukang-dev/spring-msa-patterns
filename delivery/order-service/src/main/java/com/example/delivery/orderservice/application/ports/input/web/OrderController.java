package com.example.delivery.orderservice.application.ports.input.web;

import com.example.delivery.orderservice.application.dto.OrderCommand;
import com.example.delivery.orderservice.application.ports.input.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public void order(@RequestBody OrderCommand orderCommand) {
        orderService.order(orderCommand);
    }
}
