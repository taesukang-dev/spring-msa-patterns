package com.example.coupon.couponcore.dataaccess.repository;

import com.example.coupon.couponcore.dataaccess.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponJpaRepository extends JpaRepository<CouponEntity, Long> {
}
