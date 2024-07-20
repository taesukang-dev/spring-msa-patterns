package com.example.coupon.couponconsumer.dataaccess.repository;

import com.example.coupon.couponconsumer.dataaccess.entity.CouponIssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponIssueJpaRepository extends JpaRepository<CouponIssueEntity, Long> {
}
