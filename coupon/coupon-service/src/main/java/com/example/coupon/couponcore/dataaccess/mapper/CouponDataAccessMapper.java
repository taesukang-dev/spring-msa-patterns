package com.example.coupon.couponcore.dataaccess.mapper;

import com.example.coupon.couponcore.dataaccess.entity.CouponEntity;
import com.example.coupon.couponcore.domain.Coupon;
import org.springframework.stereotype.Component;

@Component
public class CouponDataAccessMapper {

    public String getIssueRequestKey(long couponId) {
        return "issue:request:couponId:%s".formatted(couponId);
    }

    public Coupon couponEntityToCoupon(CouponEntity couponEntity) {
        return Coupon.builder()
                .id(couponEntity.getId())
                .title(couponEntity.getTitle())
                .couponType(couponEntity.getCouponType())
                .totalQuantity(couponEntity.getTotalQuantity())
                .issuedQuantity(couponEntity.getIssuedQuantity())
                .discountAmount(couponEntity.getDiscountAmount())
                .minAvailableAmount(couponEntity.getMinAvailableAmount())
                .dateIssueStart(couponEntity.getDateIssueStart())
                .dateIssueEnd(couponEntity.getDateIssueEnd())
                .build();
    }

    public CouponEntity couponToCouponEntity(Coupon coupon) {
        return CouponEntity.builder()
                .title(coupon.getTitle())
                .couponType(coupon.getCouponType())
                .totalQuantity(coupon.getTotalQuantity())
                .issuedQuantity(coupon.getIssuedQuantity())
                .discountAmount(coupon.getDiscountAmount())
                .minAvailableAmount(coupon.getMinAvailableAmount())
                .dateIssueStart(coupon.getDateIssueStart())
                .dateIssueEnd(coupon.getDateIssueEnd())
                .build();
    }
}
