package com.example.delivery.orderservice.dataaccess.adapter;

import com.example.delivery.orderservice.application.outbox.OrderApprovalOutboxMessage;
import com.example.delivery.orderservice.application.ports.output.OrderApprovalOutboxMessageRepository;
import com.example.delivery.orderservice.dataaccess.mapper.OrderDataAccessMapper;
import com.example.delivery.orderservice.dataaccess.repository.OrderApprovalOutboxMessageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderApprovalOutboxMessageRepositoryImpl implements OrderApprovalOutboxMessageRepository {

    private final OrderApprovalOutboxMessageJpaRepository oaOutboxRepository;
    private final OrderDataAccessMapper mapper;

    @Override
    public OrderApprovalOutboxMessage save(OrderApprovalOutboxMessage message) {
        return mapper.entityToOrderApprovalOutboxMessage(
                oaOutboxRepository.save(
                        mapper.outboxMessageToOrderApprovalOutboxMessageEntity(
                                message
                        )
                )
        );
    }
}
