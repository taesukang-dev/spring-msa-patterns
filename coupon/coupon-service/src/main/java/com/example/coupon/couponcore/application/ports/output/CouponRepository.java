package com.example.coupon.couponcore.application.ports.output;

import com.example.coupon.couponcore.core.Coupon;

import java.util.Optional;

public interface CouponRepository {
    Coupon save(Coupon coupon);

    Optional<Coupon> findById(Long couponId);
}
