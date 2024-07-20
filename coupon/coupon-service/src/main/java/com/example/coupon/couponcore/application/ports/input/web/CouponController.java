package com.example.coupon.couponcore.application.ports.input.web;

import com.example.coupon.couponcore.application.ports.input.CouponApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CouponController {

    private final CouponApplicationService couponApplicationService;

    @PostMapping("/coupon-issue")
    public void test(@RequestBody IssueRequestCommand issueRequestCommand) {
        couponApplicationService.issue(issueRequestCommand.couponId(), issueRequestCommand.userId());
    }
}
