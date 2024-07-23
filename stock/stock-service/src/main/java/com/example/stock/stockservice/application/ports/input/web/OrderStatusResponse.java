package com.example.stock.stockservice.application.ports.input.web;

import com.example.stock.stockservice.core.vo.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public record OrderStatusResponse(
        Long id,
        UUID productId,
        int quantity,
        OrderStatus orderStatus
) {

}
