package com.example.coupon.couponconsumer.dataaccess.adapter;

import com.example.coupon.couponconsumer.application.ports.output.CouponIssueRepository;
import com.example.coupon.couponconsumer.core.entity.CouponIssue;
import com.example.coupon.couponconsumer.dataaccess.mapper.CouponIssueDataAccessMapper;
import com.example.coupon.couponconsumer.dataaccess.repository.CouponIssueJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponIssueRepositoryImpl implements CouponIssueRepository {

    private final CouponIssueJpaRepository couponIssueJpaRepository;
    private final CouponIssueDataAccessMapper mapper;

    @Override
    public CouponIssue save(CouponIssue couponIssue) {
        return mapper.couponIssueEntityToCouponIssue(
                couponIssueJpaRepository.save(
                        mapper.couponIssueToCouponIssueEntity(couponIssue)
                )
        );
    }

}
