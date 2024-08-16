package com.example.delivery.orderservice.application;

import com.example.delivery.orderservice.application.mapper.OrderDataMapper;
import com.example.delivery.orderservice.application.outbox.OrderPaymentOutboxMessage;
import com.example.delivery.orderservice.application.ports.input.OrderService;
import com.example.delivery.orderservice.application.dto.OrderCommand;
import com.example.delivery.orderservice.application.ports.output.OrderPaymentOutboxMessageRepository;
import com.example.delivery.orderservice.application.ports.output.OrderRepository;
import com.example.delivery.orderservice.application.ports.output.RestaurantApprovalOutboxMessageRepository;
import com.example.delivery.orderservice.core.entity.Order;
import com.example.delivery.outbox.OutboxStatus;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDataMapper mapper;
    private final OrderRepository orderRepository;
    private final OrderPaymentOutboxMessageRepository orderPaymentOutboxMessageRepository;
    private final ApplicationEventPublisher publisher;

    @Override
    // TODO Transactional 로 묶어야 함
    // TODO : Move to Saga Helper
    public void order(OrderCommand orderCommand) {
        Order order = mapper.orderCommandToOrder(orderCommand);
        order.validateOrder();
        order.initOrder();

        // 1. order 저장
        orderRepository.save(order);
        // 2. payment
        // TODO : payment approval -> outbox started
        BigDecimal totalPrice = order.getTotalPrice();
        Long userId = order.getUserId();
        OrderPaymentOutboxMessage paymentOutboxMessage = orderPaymentOutboxMessageRepository.save(
                OrderPaymentOutboxMessage
                        .builder()
                        .id(UUID.randomUUID())
                        .sagaId(UUID.randomUUID())
                        .outboxStatus(OutboxStatus.STARTED)
                        .totalPrice(totalPrice)
                        .userId(userId)
                        .build()
        );
        // TODO : 1. pay
        publisher.publishEvent(
                mapper.outboxMessageToOrderPaymentEvent(paymentOutboxMessage)
        );


        // TODO FIX
        // 2. approval event outbox 저장
//        UUID sagaId = UUID.randomUUID();
//        raRepository.save(
//                mapper.orderToRestaurantApprovalOutboxMessage(order, sagaId)
//        );

        // 3. restaurant 에서 available 한지 check, kafka messaging
//        publisher.publishEvent(
//                mapper.orderToRestaurantApprovalEvent(order, sagaId)
//        );

        // TODO 2. 주문, restaurant valid check -> cqrs
    }

    // TODO : 결제, scheduler

    @Override
    public void cancel() {

    }
}
