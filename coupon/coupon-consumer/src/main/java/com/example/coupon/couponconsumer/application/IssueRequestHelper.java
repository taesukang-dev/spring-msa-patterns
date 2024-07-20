package com.example.coupon.couponconsumer.application;

import com.example.coupon.couponconsumer.application.dto.CouponIssueRequest;
import com.example.coupon.couponconsumer.application.mapper.CouponIssueMapper;
import com.example.coupon.couponconsumer.application.ports.output.CouponIssueRepository;
import com.example.coupon.couponconsumer.core.entity.CouponIssue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class IssueRequestHelper {
    private final CouponIssueRepository couponIssueRepository;
    private final CouponIssueMapper mapper;

    @Transactional
    public CouponIssue persistCouponIssue(CouponIssueRequest issueRequest) {
        return couponIssueRepository.save(mapper.issueRequestToCouponIssue(issueRequest));
    }

}
