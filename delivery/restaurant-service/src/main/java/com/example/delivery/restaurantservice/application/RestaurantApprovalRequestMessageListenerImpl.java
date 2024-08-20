package com.example.delivery.restaurantservice.application;

import com.example.delivery.restaurantservice.application.dto.RestaurantApprovalRequest;
import com.example.delivery.restaurantservice.application.ports.input.RestaurantApprovalRequestMessageListener;
import com.example.delivery.restaurantservice.application.ports.output.RestaurantRepository;
import com.example.delivery.restaurantservice.core.entity.Restaurant;
import com.example.delivery.restaurantservice.core.event.RestaurantApprovalResponseEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


import static com.example.delivery.infrastructure.vo.OrderStatus.PAID;

@RequiredArgsConstructor
@Component
public class RestaurantApprovalRequestMessageListenerImpl implements RestaurantApprovalRequestMessageListener {

    private final RestaurantRepository restaurantRepository;
    private final ApplicationEventPublisher publisher;

    @Override
    @Transactional
    public void approveOrder(RestaurantApprovalRequest restaurantApprovalRequest) {
        if (!restaurantApprovalRequest.getOrderStatus().equals(PAID)) {
            throw new RuntimeException("ApprovalRequest is not in correct state for operation");
        }

        Restaurant restaurant = restaurantRepository.findById(
                restaurantApprovalRequest.getRestaurantId(),
                restaurantApprovalRequest.getProductIds()
        ).orElseThrow(() -> new RuntimeException("Restaurant Not Found"));

        // 주문 접수, 음식점에 알림... 배달 서비스...

        publisher.publishEvent(
                RestaurantApprovalResponseEvent
                        .builder()
                        .sagaId(restaurantApprovalRequest.getSagaId())
                        .orderId(restaurantApprovalRequest.getOrderId())
                        .userId(restaurantApprovalRequest.getUserId())
                        .restaurantId(restaurantApprovalRequest.getRestaurantId())
                        .orderStatus(restaurantApprovalRequest.getOrderStatus())
                        .result(restaurant.checkAvailable())
                        .build()
        );
    }
}
