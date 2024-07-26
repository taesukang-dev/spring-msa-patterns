package com.example.stock.stockservice.application.ports.input.web;

import java.util.UUID;

public record OrderStatusCommand(
        UUID orderId
) {
}
