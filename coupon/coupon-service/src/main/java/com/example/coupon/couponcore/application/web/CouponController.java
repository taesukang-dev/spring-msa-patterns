package com.example.coupon.couponcore.application.web;

import com.example.coupon.couponcore.application.ports.input.CouponApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CouponController {

    private final CouponApplicationService couponApplicationService;

    @GetMapping("/test")
    public void test() {
        couponApplicationService.issue(1L, 1L);
    }
}
