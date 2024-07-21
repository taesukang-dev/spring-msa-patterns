package com.example.stock.stockservice.core.event;

import lombok.Data;

import java.util.UUID;

@Data
public class StockBuyEvent {
    UUID productId;
    int quantity;
}
