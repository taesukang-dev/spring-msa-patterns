package com.example.coupon.couponconsumer.application.dto;

public record CouponIssueRequest(
        Long couponId,
        Long userId
) {

}
