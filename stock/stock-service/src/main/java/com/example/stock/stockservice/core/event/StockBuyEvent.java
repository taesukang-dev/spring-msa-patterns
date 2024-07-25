package com.example.stock.stockservice.core.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
public class StockBuyEvent {
    UUID productId;
    Long userId;
    int quantity;
}
