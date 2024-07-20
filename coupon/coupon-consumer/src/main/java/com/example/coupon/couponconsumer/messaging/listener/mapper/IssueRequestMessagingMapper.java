package com.example.coupon.couponconsumer.messaging.listener.mapper;

import com.example.coupon.couponcommon.infrastructure.model.CouponIssueAvroModel;
import com.example.coupon.couponconsumer.application.dto.CouponIssueRequest;
import org.springframework.stereotype.Component;

@Component
public class IssueRequestMessagingMapper {

    public CouponIssueRequest issueRequestAvroModelToIssueRequest(CouponIssueAvroModel avroModel) {
        return new CouponIssueRequest(
                avroModel.getCouponId(),
                avroModel.getUserId()
        );
    }

}
