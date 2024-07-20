package com.example.coupon.couponcore.core.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponIssueEvent {
    Long id;
    Long couponId;
    Long userId;

    public CouponIssueEvent(Long couponId, Long userId) {
        this.couponId = couponId;
        this.userId = userId;
    }
}
