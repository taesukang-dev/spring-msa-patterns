package com.example.delivery.orderservice.core.event;

import com.example.delivery.infrastructure.vo.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.kafka.support.SendResult;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RestaurantApprovalEvent {
    private UUID sagaId;
    private UUID orderId;
    private Long userId;
    private UUID restaurantId;
    private String deliveryAddress;
    private BigDecimal totalPrice;
    private List<UUID> productIds;
    private UUID trackingId;
    private OrderStatus orderStatus;
    private BiConsumer<SendResult<String, ?>, Throwable> callback;
}
