package com.example.coupon.couponcore.application.ports.input;

public interface CouponApplicationService {
    void issue(Long couponId, Long userId);
}
