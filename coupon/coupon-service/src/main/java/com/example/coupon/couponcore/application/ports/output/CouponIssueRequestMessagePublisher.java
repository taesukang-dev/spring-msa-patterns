package com.example.coupon.couponcore.application.ports.output;

import com.example.coupon.couponcore.domain.event.CouponIssueEvent;

public interface CouponIssueRequestMessagePublisher {
    void publish(CouponIssueEvent couponIssueEvent);
}
