package com.example.stock.stockservice.dataaccess.adapter;

import com.example.stock.stockservice.application.ports.output.OrderRepository;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.dataaccess.mapper.StockDataAccessMapper;
import com.example.stock.stockservice.dataaccess.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final StockDataAccessMapper mapper;

    @Override
    public Order save(Order order) {
        return mapper.orderEntityToOrder(
                orderJpaRepository.save(
                        mapper.orderToOrderEntity(order)
                )
        );
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderJpaRepository.findById(id)
                .map(mapper::orderEntityToOrder);
    }
}
