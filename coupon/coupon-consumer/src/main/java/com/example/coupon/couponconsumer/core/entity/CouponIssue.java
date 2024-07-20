package com.example.coupon.couponconsumer.core.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CouponIssue {
    Long couponId;
    Long userId;
}
