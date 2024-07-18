package com.example.coupon.couponcore.messaging;

import com.example.coupon.couponcommon.infrastructure.model.CouponIssueAvroModel;
import com.example.coupon.couponcore.domain.event.CouponIssueEvent;
import org.springframework.stereotype.Component;

@Component
public class CouponIssueMessagingMapper {

    public CouponIssueAvroModel couponIssueEventToAvroModel(CouponIssueEvent couponIssueEvent) {
        return CouponIssueAvroModel
                .newBuilder()
                .setUserId(couponIssueEvent.getUserId())
                .setCouponId(couponIssueEvent.getCouponId())
                .build();
    }

}
