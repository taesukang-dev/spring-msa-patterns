package com.example.delivery.orderservice.dataaccess.adapter;

import com.example.delivery.orderservice.application.outbox.OrderPaymentOutboxMessage;
import com.example.delivery.orderservice.application.ports.output.OrderPaymentOutboxMessageRepository;
import com.example.delivery.orderservice.dataaccess.mapper.OrderDataAccessMapper;
import com.example.delivery.orderservice.dataaccess.repository.OrderPaymentOutboxMessageJpaRepository;
import com.example.delivery.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Optional<OrderPaymentOutboxMessage> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::entityToOrderPaymentOutboxMessage);
    }

    @Override
    public Optional<List<OrderPaymentOutboxMessage>> findByStatus(OutboxStatus status) {
        return Optional.of(
                repository.findByOutboxStatus(status)
                        .orElseThrow(() -> new RuntimeException("Outbox Messages Not Found"))
                        .stream().map(mapper::entityToOrderPaymentOutboxMessage)
                        .toList()
        );
    }
}
