package com.example.coupon.couponcore.application.ports.output;

import com.example.coupon.couponcore.core.event.CouponIssueEvent;

public interface CouponIssueRequestMessagePublisher {
    void publish(CouponIssueEvent couponIssueEvent);
}
