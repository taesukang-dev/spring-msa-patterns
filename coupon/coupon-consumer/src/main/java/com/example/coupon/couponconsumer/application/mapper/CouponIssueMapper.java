package com.example.coupon.couponconsumer.application.mapper;

import com.example.coupon.couponconsumer.application.dto.CouponIssueRequest;
import com.example.coupon.couponconsumer.core.entity.CouponIssue;
import org.springframework.stereotype.Component;

@Component
public class CouponIssueMapper {

    public CouponIssue issueRequestToCouponIssue(CouponIssueRequest issueRequest) {
        return CouponIssue
                .builder()
                .userId(issueRequest.userId())
                .couponId(issueRequest.couponId())
                .build();
    }
}
