package com.example.stock.stockservice.messaging.mapper;

import com.example.stock.common.infrastructure.model.OrderAvroModel;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderMessagingMapper {

    public OrderAvroModel stockBuyEventToOrderAvroModel(StockBuyEvent stockBuyEvent) {
        return OrderAvroModel.newBuilder()
                .setProductId(stockBuyEvent.getProductId())
                .setUserId(stockBuyEvent.getUserId())
                .setQuantity(stockBuyEvent.getQuantity())
                .build();
    }
}
