package com.example.delivery.orderservice.dataaccess.adapter;

import com.example.delivery.orderservice.application.outbox.RestaurantApprovalOutboxMessage;
import com.example.delivery.orderservice.application.ports.output.RestaurantApprovalOutboxMessageRepository;
import com.example.delivery.orderservice.dataaccess.mapper.OrderDataAccessMapper;
import com.example.delivery.orderservice.dataaccess.repository.RestaurantApprovalOutboxMessageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
