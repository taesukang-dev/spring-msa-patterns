package com.example.delivery.orderservice.dataaccess.adapter;

import com.example.delivery.orderservice.application.outbox.RestaurantApprovalOutboxMessage;
import com.example.delivery.orderservice.application.ports.output.RestaurantApprovalOutboxMessageRepository;
import com.example.delivery.orderservice.dataaccess.mapper.OrderDataAccessMapper;
import com.example.delivery.orderservice.dataaccess.repository.RestaurantApprovalOutboxMessageJpaRepository;
import com.example.delivery.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class RestaurantApprovalOutboxMessageRepositoryImpl implements RestaurantApprovalOutboxMessageRepository {

    private final RestaurantApprovalOutboxMessageJpaRepository repository;
    private final OrderDataAccessMapper mapper;

    @Override
    public RestaurantApprovalOutboxMessage save(RestaurantApprovalOutboxMessage outboxMessage) {
        return mapper.restaurantOutboxMessageEntityToMessage(
                repository.save(
                        mapper.restaurantOutboxMessageToEntity(outboxMessage)
                )
        );
    }

    @Override
    public Optional<RestaurantApprovalOutboxMessage> findBySagaId(UUID sagaId) {
        return repository.findBySagaId(sagaId)
                .map(mapper::restaurantOutboxMessageEntityToMessage);
    }

    @Override
    public Optional<List<RestaurantApprovalOutboxMessage>> findByStatus(OutboxStatus status) {
        return Optional.of(
                repository.findByOutboxStatus(status)
                        .orElseThrow(() -> new RuntimeException("Outbox Messages Not Found"))
                        .stream().map(mapper::restaurantOutboxMessageEntityToMessage)
                        .toList()
        );
    }
}
