package com.example.coupon.couponconsumer.dataaccess.mapper;

import com.example.coupon.couponconsumer.core.entity.CouponIssue;
import com.example.coupon.couponconsumer.dataaccess.entity.CouponIssueEntity;
import org.springframework.stereotype.Component;

@Component
public class CouponIssueDataAccessMapper {

    public CouponIssueEntity couponIssueToCouponIssueEntity(CouponIssue couponIssue) {
        return CouponIssueEntity
                .builder()
                .couponId(couponIssue.getCouponId())
                .userId(couponIssue.getUserId())
                .build();
    }

    public CouponIssue couponIssueEntityToCouponIssue(CouponIssueEntity couponIssueEntity) {
        return CouponIssue.builder()
                .couponId(couponIssueEntity.getCouponId())
                .userId(couponIssueEntity.getUserId())
                .build();
    }
}
