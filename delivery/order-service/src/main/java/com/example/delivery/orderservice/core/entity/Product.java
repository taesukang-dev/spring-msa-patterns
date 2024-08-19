package com.example.delivery.orderservice.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class Product {
    private UUID productId;
    private String name;
    private BigDecimal price;
    private boolean isAvailable;
}
