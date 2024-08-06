package com.example.delivery.orderservice.application;

import com.example.delivery.orderservice.application.mapper.OrderDataMapper;
import com.example.delivery.orderservice.application.ports.input.OrderService;
import com.example.delivery.orderservice.application.dto.OrderCommand;
import com.example.delivery.orderservice.application.ports.output.OrderRepository;
import com.example.delivery.orderservice.application.ports.output.RestaurantApprovalOutboxMessageRepository;
import com.example.delivery.orderservice.core.entity.Order;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDataMapper mapper;
    private final OrderRepository orderRepository;
    private final RestaurantApprovalOutboxMessageRepository raRepository;
    private final ApplicationEventPublisher publisher;

    @Override
    // TODO Transactional 로 묶어야 함
    public void order(OrderCommand orderCommand) {
        Order order = mapper.orderCommandToOrder(orderCommand);
        order.validateOrder();
        order.initOrder();

        // 1. order 저장
        orderRepository.save(order);

        // 2. approval event outbox 저장
        UUID sagaId = UUID.randomUUID();
        raRepository.save(
                mapper.orderToRestaurantApprovalOutboxMessage(order, sagaId)
        );

        // 3. restaurant 에서 available 한지 check, kafka messaging
        publisher.publishEvent(
                mapper.orderToRestaurantApprovalEvent(order, sagaId)
        );
    }

    // TODO : 결제, scheduler

    @Override
    public void cancel() {

    }
}
