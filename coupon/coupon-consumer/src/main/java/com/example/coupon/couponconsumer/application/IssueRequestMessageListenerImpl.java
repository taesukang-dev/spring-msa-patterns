package com.example.coupon.couponconsumer.application;

import com.example.coupon.couponconsumer.application.dto.CouponIssueRequest;
import com.example.coupon.couponconsumer.application.ports.input.message.listener.IssueRequestMessageListener;
import com.example.coupon.couponconsumer.core.entity.CouponIssue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueRequestMessageListenerImpl implements IssueRequestMessageListener {

    private final IssueRequestHelper issueRequestHelper;

    @Override
    public void completeIssueRequest(CouponIssueRequest issueRequest) {
        CouponIssue couponIssue = issueRequestHelper.persistCouponIssue(issueRequest);
        log.info("user : {}, coupon : {}, issued", couponIssue.getUserId(), couponIssue.getCouponId());
    }
}
