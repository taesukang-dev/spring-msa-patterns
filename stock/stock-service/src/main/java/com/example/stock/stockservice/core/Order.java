package com.example.stock.stockservice.core;

import com.example.stock.stockservice.core.vo.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class Order {
    private UUID id;
    private Long userId;
    private UUID productId;
    private int quantity;
    private OrderStatus orderStatus;
    private Long version;

    public Order updateStatus(OrderStatus status) {
        this.orderStatus = status;
        return this;
    }
}
