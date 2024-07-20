package com.example.coupon.couponconsumer.application.ports.input.message.listener;

import com.example.coupon.couponconsumer.application.dto.CouponIssueRequest;

public interface IssueRequestMessageListener {
    void completeIssueRequest(CouponIssueRequest issueRequest);
}
