package com.example.coupon.couponcore.dataaccess.adapter;

import com.example.coupon.couponcore.dataaccess.mapper.CouponDataAccessMapper;
import com.example.coupon.couponcore.dataaccess.repository.CouponJpaRepository;
import com.example.coupon.couponcore.domain.Coupon;
import com.example.coupon.couponcore.application.ports.output.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CouponRepositoryImpl implements CouponRepository {

    private final CouponDataAccessMapper couponDataAccessMapper;
    private final CouponJpaRepository couponJpaRepository;

    @Override
    public Coupon save(Coupon coupon) {
        return couponDataAccessMapper.couponEntityToCoupon(
                        couponJpaRepository.save(couponDataAccessMapper.couponToCouponEntity(coupon))
                );
    }

    @Override
    public Optional<Coupon> findById(Long couponId) {
        return couponJpaRepository.findById(couponId)
                .map(couponDataAccessMapper::couponEntityToCoupon);
    }
}
