package com.example.stock.stockservice.application;

import com.example.stock.common.aop.DistributedLock;
import com.example.stock.common.infrastructure.outbox.OutboxStatus;
import com.example.stock.stockservice.application.dto.PaymentResponse;
import com.example.stock.stockservice.application.ports.output.OrderOutboxRepository;
import com.example.stock.stockservice.application.ports.output.OrderRepository;
import com.example.stock.stockservice.application.ports.output.StockRepository;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.Stock;
import com.example.stock.stockservice.core.outbox.OrderOutboxMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.stock.stockservice.core.vo.OrderStatus.CANCELLED;
import static com.example.stock.stockservice.core.vo.OrderStatus.PAID;

@RequiredArgsConstructor
@Component
public class OrderPaymentSaga {

    private final StockRepository stockRepository;
    private final OrderRepository orderRepository;
    private final OrderOutboxRepository orderOutboxRepository;

    @Transactional
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

    @Transactional
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
        // revokeStock(...)
    }

//    @DistributedLock
//    public void revokeStock() {
//        // 재고 되돌리기
//        Stock stock = stockRepository.findById(order.getProductId())
//                .orElseThrow(() -> new RuntimeException("Stock Not Found"));
//        stock.updateAvailableQuantity(
//                stock.getAvailableQuantity() + order.getQuantity()
//        );
//        stockRepository.save(stock);
//    }
}
