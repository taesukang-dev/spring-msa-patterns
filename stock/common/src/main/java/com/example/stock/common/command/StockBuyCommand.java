package com.example.stock.common.command;

import java.util.UUID;

public record StockBuyCommand(
        UUID productId,
        Long userId,
        int quantity
) {
}
