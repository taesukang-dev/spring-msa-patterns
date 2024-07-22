package com.example.stock.stockservice.core;

import com.example.stock.stockservice.core.vo.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class Order {
    private Long id;
    private UUID productId;
    private int quantity;
    private OrderStatus orderStatus;

    public Order updateStatus(OrderStatus status) {
        this.orderStatus = status;
        return this;
    }
}
