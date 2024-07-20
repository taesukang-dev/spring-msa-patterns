package com.example.coupon.couponcore.application.ports.input.web;

public record IssueRequestCommand(
        Long couponId,
        Long userId
) {
}
