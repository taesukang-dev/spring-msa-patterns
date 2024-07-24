package com.example.coupon.common.command;

import java.util.UUID;

public record StockBuyCommand(
        UUID productId,
        int quantity
) {
}
