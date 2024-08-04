package com.example.delivery.orderservice.application.mapper;

import com.example.delivery.orderservice.application.dto.OrderCommand;
import com.example.delivery.orderservice.application.dto.OrderItemCommand;
import com.example.delivery.orderservice.core.entity.Order;
import com.example.delivery.orderservice.core.entity.OrderItem;
import com.example.delivery.orderservice.core.entity.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderDataMapper {

    public Order orderCommandToOrder(OrderCommand orderCommand) {
        return Order
                .builder()
                .userId(orderCommand.userId())
                .restaurantId(orderCommand.restaurantId())
                .totalPrice(orderCommand.totalPrice())
                .orderItems(orderCommand.items().stream().map(this::orderItemCommandToOrderItem).collect(Collectors.toList()))
                .deliveryAddress(orderCommand.deliveryAddress())
                .build();
    }

    private OrderItem orderItemCommandToOrderItem(OrderItemCommand orderItemCommand) {
        return OrderItem
                .builder()
                .product(new Product(orderItemCommand.productId()))
                .quantity(orderItemCommand.quantity())
                .price(orderItemCommand.price())
                .subTotal(orderItemCommand.subTotal())
                .build();
    }

}
