package com.example.stock.stockservice.messaging.publisher.kafka;

import com.example.stock.common.infrastructure.model.OrderAvroModel;
import com.example.stock.stockservice.application.ports.output.OrderRequestMessagePublisher;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import com.example.stock.stockservice.messaging.mapper.OrderMessagingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.example.stock.common.infrastructure.kafka.KafkaConst.ORDER_TOPIC;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderRequestMessageKafkaPublisher implements OrderRequestMessagePublisher {

    private final KafkaTemplate<String, OrderAvroModel> kafkaTemplate;
    private final OrderMessagingMapper mapper;


    @Override
    public void publish(StockBuyEvent stockBuyEvent) {
        kafkaTemplate.send(ORDER_TOPIC,
                mapper.stockBuyEventToOrderAvroModel(stockBuyEvent));
    }
}
