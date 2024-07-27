package com.example.stock.stockservice.application;

import com.example.stock.common.aop.DistributedLock;
import com.example.stock.common.command.StockBuyCommand;
import com.example.stock.common.infrastructure.outbox.OutboxStatus;
import com.example.stock.stockservice.application.mapper.StockDataMapper;
import com.example.stock.stockservice.application.ports.output.OrderOutboxRepository;
import com.example.stock.stockservice.application.ports.output.OrderRepository;
import com.example.stock.stockservice.application.ports.output.StockRepository;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.Stock;
import com.example.stock.stockservice.core.outbox.OrderOutboxMessage;
import com.example.stock.stockservice.core.vo.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class StockServiceHelper {
    private final StockRepository stockRepository;
    private final OrderRepository orderRepository;
    private final OrderOutboxRepository orderOutboxRepository;
    private final StockDataMapper mapper;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    @Transactional
    public Order buy(StockBuyCommand stockBuyCommand) {
        // 재고 처리
        checkQuantityAndUpdate(stockBuyCommand);
        Order pendingOrder = orderRepository.save(
                createOrder(stockBuyCommand)
        );
        // Outbox 저장
        orderOutboxRepository.save(
                OrderOutboxMessage.builder()
                        .id(UUID.randomUUID())
                        .orderId(pendingOrder.getId())
                        .userId(stockBuyCommand.userId())
                        .productId(stockBuyCommand.productId())
                        .quantity(stockBuyCommand.quantity())
                        .orderStatus(pendingOrder.getOrderStatus())
                        .outboxStatus(OutboxStatus.STARTED)
                        .build()
        );
        // @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT) 로 위 처리들이 정상적으로 수행됐다면 실행
        publisher.publishEvent(
                mapper.stockBuyCommandToEvent(stockBuyCommand, pendingOrder.getId())
        );
        return pendingOrder;
    }

    @DistributedLock
    private void checkQuantityAndUpdate(StockBuyCommand stockBuyCommand) {
        Stock stock = stockRepository.findById(stockBuyCommand.productId())
                .orElseThrow(() -> new RuntimeException("Item Not Found"));
        if (!stock.isAvailableToBuy(stockBuyCommand.quantity())) {
            throw new RuntimeException("Not available to buy");
        }

        int updateQuantity = stock.getAvailableQuantity() - stockBuyCommand.quantity();
        stock.updateAvailableQuantity(updateQuantity);

        try {
            stockRepository.save(stock);
        } catch (OptimisticLockingFailureException e) {
            throw new RuntimeException("Lost Update Occurred");
        }
    }

    private Order createOrder(StockBuyCommand stockBuyCommand) {
        UUID id = UUID.randomUUID();
        return Order.builder()
                .id(id)
                .productId(stockBuyCommand.productId())
                .userId(stockBuyCommand.userId())
                .quantity(stockBuyCommand.quantity())
                .orderStatus(OrderStatus.PENDING)
                .build();
    }

}
