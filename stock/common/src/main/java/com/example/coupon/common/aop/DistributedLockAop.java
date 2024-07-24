package com.example.coupon.common.aop;

import com.example.coupon.common.command.StockBuyCommand;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAop {

    private static final String REDIS_LOCK_PREFIX = "LOCK:";

    private final RedissonClient redissonClient;
    private final MyTransactionManager myTransactionManager;

    @Around("@annotation(com.example.coupon.common.aop.DistributedLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

        String key = REDIS_LOCK_PREFIX + getParameterizedReservationRequest(joinPoint).productId();
        RLock lock = redissonClient.getLock(key);

        try {
            boolean isLocked = lock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit());
            if (!isLocked) {
                return false;
            }
            return myTransactionManager.proceed(joinPoint);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error occurred when retrieving redisson lock");
        } finally {
            lock.unlock();
        }
    }

    private StockBuyCommand getParameterizedReservationRequest(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        StockBuyCommand stockBuyCommand = null;
        for (Object arg : args) {
            if (arg instanceof StockBuyCommand) {
                stockBuyCommand = (StockBuyCommand) arg;
                break;
            }
        }
        return stockBuyCommand;
    }
}