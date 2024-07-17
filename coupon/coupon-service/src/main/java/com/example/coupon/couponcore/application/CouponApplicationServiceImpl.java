package com.example.coupon.couponcore.application;

import com.example.coupon.couponcore.domain.Coupon;
import com.example.coupon.couponcore.application.ports.input.CouponApplicationService;
import com.example.coupon.couponcore.application.ports.output.AtomicCouponIssueRepository;
import com.example.coupon.couponcore.application.ports.output.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponApplicationServiceImpl implements CouponApplicationService {

    private final CouponRepository couponRepository;
    private final AtomicCouponIssueRepository atomicCouponIssueRepository;

    @Override
    public void issue(Long couponId, Long userId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new RuntimeException("Coupon 이 없습니다."));
        if (!coupon.issuableCoupon()) {
            throw new RuntimeException("Not Issuable Coupon");
        }
        atomicCouponIssueRepository.issueRequest(couponId, userId, coupon.getTotalQuantity());
        // TODO : Kafka Messaging
    }
}