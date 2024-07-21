package com.example.stock.stockservice.core;

import com.example.stock.stockservice.core.vo.OrderStatus;
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
    private OrderStatus orderStatus;

    public boolean isAvailableToBuy(int quantity) {
        return availableQuantity > quantity;
    }

    public Stock updateStatus(OrderStatus status) {
        this.orderStatus = status;
        return this;
    }
}
