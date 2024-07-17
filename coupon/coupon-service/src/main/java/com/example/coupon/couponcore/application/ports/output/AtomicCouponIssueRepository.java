package com.example.coupon.couponcore.application.ports.output;

public interface AtomicCouponIssueRepository {
    void issueRequest(Long couponId, Long userId, int totalIssueQuantity);
}
