package com.example.delivery.orderservice.core.entity;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Builder
@Getter
public class OrderItem {
    private int orderItemId;
    private UUID orderId;
    private final Product product;
    private final int quantity;
    private final BigDecimal price;
    private final BigDecimal subTotal;

    void initialOrderItem(UUID orderId, int orderItemId) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
    }

    boolean isPriceValid() {
        return (price != null && price.compareTo(BigDecimal.ZERO) > 0) &&
                setScale(price.multiply(new BigDecimal(quantity))).equals(subTotal);
    }

    private BigDecimal setScale(BigDecimal in) {
        return in.setScale(2, RoundingMode.HALF_EVEN);
    }
}
