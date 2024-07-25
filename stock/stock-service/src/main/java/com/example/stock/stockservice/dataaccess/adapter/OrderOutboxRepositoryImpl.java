package com.example.stock.stockservice.dataaccess.adapter;

import com.example.stock.stockservice.application.ports.output.OrderOutboxRepository;
import com.example.stock.stockservice.core.outbox.OrderOutboxMessage;
import com.example.stock.stockservice.dataaccess.mapper.StockDataAccessMapper;
import com.example.stock.stockservice.dataaccess.repository.OrderOutBoxJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

}
