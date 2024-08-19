package com.example.delivery.orderservice.core.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class PaymentCompensateEvent {
    private UUID orderId;
    private UUID sagaId;
    private Long userId;
    private boolean result;
}
