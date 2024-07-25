package com.example.stock.stockservice.dataaccess.entity;

import com.example.stock.stockservice.core.vo.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private UUID productId;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
