package com.example.stock.stockservice.application.ports.input.web;

import com.example.stock.stockservice.core.vo.OrderStatus;
import lombok.Builder;

import java.util.UUID;

@Builder
public record OrderStatusResponse(
        UUID id,
        UUID productId,
        int quantity,
        OrderStatus orderStatus
) {

}
