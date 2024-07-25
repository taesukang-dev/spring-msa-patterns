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
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        checkQuantityAndDecrease(stockBuyCommand);
        Order pendingOrder = orderRepository.save(
                mapper.stockBuyCommandToOrder(stockBuyCommand)
        );

        orderOutboxRepository.save(
                OrderOutboxMessage.builder()
                        .orderId(pendingOrder.getId())
                        .userId(stockBuyCommand.userId())
                        .productId(stockBuyCommand.productId())
                        .quantity(stockBuyCommand.quantity())
                        .orderStatus(pendingOrder.getOrderStatus())
                        .outboxStatus(OutboxStatus.STARTED)
                        .build()
        );
        publisher.publishEvent(mapper.stockBuyCommandToEvent(stockBuyCommand));
        return pendingOrder;
    }

    @DistributedLock
    private void checkQuantityAndDecrease(StockBuyCommand stockBuyCommand) {
        Stock stock = stockRepository.findById(stockBuyCommand.productId())
                .orElseThrow(() -> new RuntimeException("Item Not Found"));
        if (!stock.isAvailableToBuy(stockBuyCommand.quantity())) {
            throw new RuntimeException("Not available to buy");
        }

        boolean updated = stockRepository.decreaseQuantity(
                stockBuyCommand.productId(),
                stock.getAvailableQuantity() - stockBuyCommand.quantity()
        );
        if (!updated) {
            throw new RuntimeException("Out of Stock");
        }
    }

}
