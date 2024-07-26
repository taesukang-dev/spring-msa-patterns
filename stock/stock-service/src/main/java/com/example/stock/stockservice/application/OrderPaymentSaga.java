package com.example.stock.stockservice.application;

import com.example.stock.common.infrastructure.outbox.OutboxStatus;
import com.example.stock.stockservice.application.dto.PaymentResponse;
import com.example.stock.stockservice.application.ports.output.OrderOutboxRepository;
import com.example.stock.stockservice.application.ports.output.OrderRepository;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.outbox.OrderOutboxMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.stock.stockservice.core.vo.OrderStatus.CANCELLED;
import static com.example.stock.stockservice.core.vo.OrderStatus.PAID;

@RequiredArgsConstructor
@Component
public class OrderPaymentSaga {

    private final OrderRepository orderRepository;
    private final OrderOutboxRepository orderOutboxRepository;


    public void process(PaymentResponse paymentResponse) {
        // order -> PAID
        Order order = orderRepository.findById(paymentResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));
        orderRepository.save(order.updateStatus(PAID));

        // outbox -> COMPLETED
        OrderOutboxMessage orderOutboxMessage = orderOutboxRepository.findByOrderId(paymentResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("Message Not Found"));
        orderOutboxMessage.updateStatus(OutboxStatus.COMPLETED);
        orderOutboxRepository.save(orderOutboxMessage);
    }

    public void rollback(PaymentResponse paymentResponse) {
        // order -> CANCELLED
        Order order = orderRepository.findById(paymentResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));
        orderRepository.save(order.updateStatus(CANCELLED));
        // outbox -> FAILED
        OrderOutboxMessage orderOutboxMessage = orderOutboxRepository.findByOrderId(paymentResponse.getOrderId())
                .orElseThrow(() -> new RuntimeException("Message Not Found"));
        orderOutboxMessage.updateStatus(OutboxStatus.FAILED);
        orderOutboxRepository.save(orderOutboxMessage);
    }
}
