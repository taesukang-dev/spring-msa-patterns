package com.example.delivery.orderservice.dataaccess.adapter;

import com.example.delivery.orderservice.application.ports.output.OrderRepository;
import com.example.delivery.orderservice.core.entity.Order;
import com.example.delivery.orderservice.dataaccess.mapper.OrderDataAccessMapper;
import com.example.delivery.orderservice.dataaccess.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderDataAccessMapper mapper;

    @Override
    public Order save(Order order) {
        return mapper.orderEntityToOrder(
                orderJpaRepository.save(
                        mapper.orderToOrderEntity(order)
                )
        );
    }

    @Override
    public Optional<Order> findById(UUID orderId) {
        return orderJpaRepository.findById(orderId)
                .map(mapper::orderEntityToOrder);
    }
}
