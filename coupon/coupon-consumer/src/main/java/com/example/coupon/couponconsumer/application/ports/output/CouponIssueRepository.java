package com.example.coupon.couponconsumer.application.ports.output;

import com.example.coupon.couponconsumer.core.entity.CouponIssue;

public interface CouponIssueRepository {
    CouponIssue save(CouponIssue couponIssue);
}
