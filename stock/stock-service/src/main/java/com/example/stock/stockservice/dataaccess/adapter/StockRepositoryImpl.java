package com.example.stock.stockservice.dataaccess.adapter;

import com.example.stock.stockservice.application.ports.output.StockRepository;
import com.example.stock.stockservice.core.Stock;
import com.example.stock.stockservice.dataaccess.mapper.StockDataAccessMapper;
import com.example.stock.stockservice.dataaccess.repository.StockJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class StockRepositoryImpl implements StockRepository {
    private static final int ONLY_ONE_AFFECTED_ROW = 1;

    private final StockJpaRepository stockJpaRepository;
    private final StockDataAccessMapper mapper;

    @Override
    public Optional<Stock> findById(UUID productId) {
        return stockJpaRepository.findById(productId)
                .map(mapper::stockEntityToStock);
    }

    @Override
    public Stock save(Stock stock) {
        return mapper.stockEntityToStock(
                stockJpaRepository.save(mapper.stockToStockEntity(stock))
        );
    }

    @Override
    public boolean decreaseQuantity(UUID productId, int quantity) {
        Integer result = null;
        try {
            result = stockJpaRepository.decreaseQuantity(productId, quantity);
        } catch (OptimisticLockingFailureException e) {
            throw new RuntimeException("Lost Update Occurred");
        }
        return result == ONLY_ONE_AFFECTED_ROW;
    }
}
