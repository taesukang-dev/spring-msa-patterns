package com.example.stock.stockservice.core.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class StockBuyEvent {
    UUID productId;
    int quantity;
}
