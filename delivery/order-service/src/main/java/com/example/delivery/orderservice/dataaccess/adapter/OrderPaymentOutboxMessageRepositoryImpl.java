package com.example.delivery.orderservice.dataaccess.adapter;

import com.example.delivery.orderservice.application.outbox.OrderPaymentOutboxMessage;
import com.example.delivery.orderservice.application.ports.output.OrderPaymentOutboxMessageRepository;
import com.example.delivery.orderservice.dataaccess.mapper.OrderDataAccessMapper;
import com.example.delivery.orderservice.dataaccess.repository.OrderPaymentOutboxMessageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderPaymentOutboxMessageRepositoryImpl implements OrderPaymentOutboxMessageRepository {

    private final OrderPaymentOutboxMessageJpaRepository repository;
    private final OrderDataAccessMapper mapper;

    @Override
    public OrderPaymentOutboxMessage save(OrderPaymentOutboxMessage orderPaymentOutboxMessage) {
        return mapper.entityToOrderPaymentOutboxMessage(
                repository.save(
                        mapper.messageToOrderPaymentOutboxMessageEntity(orderPaymentOutboxMessage)
                )
        );
    }
}
