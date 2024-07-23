package com.example.stock.stockservice.application.ports.input.web;

import java.util.UUID;

public record StockBuyCommand(
        UUID productId,
        int quantity
) {
}
