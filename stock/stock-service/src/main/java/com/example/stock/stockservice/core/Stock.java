package com.example.stock.stockservice.core;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class Stock {
    private UUID productId;
    private String brandId;
    private int price;
    private int totalQuantity;
    private int availableQuantity;
    private Long version;


    public boolean isAvailableToBuy(int quantity) {
        return availableQuantity - quantity >= 0;
    }

    public int updateAvailableQuantity(int quantity) {
        this.availableQuantity = quantity;
        return this.availableQuantity;
    }
}
