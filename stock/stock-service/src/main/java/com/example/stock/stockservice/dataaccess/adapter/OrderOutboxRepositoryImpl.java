package com.example.stock.stockservice.dataaccess.adapter;

import com.example.stock.stockservice.application.ports.output.OrderOutboxRepository;
import com.example.stock.stockservice.core.outbox.OrderOutboxMessage;
import com.example.stock.stockservice.dataaccess.mapper.StockDataAccessMapper;
import com.example.stock.stockservice.dataaccess.repository.OrderOutBoxJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class OrderOutboxRepositoryImpl implements OrderOutboxRepository {

    private final StockDataAccessMapper mapper;
    private final OrderOutBoxJpaRepository orderOutBoxJpaRepository;

    @Override
    public OrderOutboxMessage save(OrderOutboxMessage orderOutboxMessage) {
        return mapper.orderOutboxEntityToOrderOutBoxMessage(
                orderOutBoxJpaRepository.save(
                        mapper.orderOutboxMessageToOrderOutboxEntity(orderOutboxMessage)
                )
        );
    }

    @Override
    public Optional<OrderOutboxMessage> findByOrderId(UUID id) {
        return orderOutBoxJpaRepository.findByOrderId(id)
                .map(mapper::orderOutboxEntityToOrderOutBoxMessage);
    }

}
