package com.example.coupon.couponcore.core;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Coupon {
    private Long id;
    private String title;
    private CouponType couponType;
    private Integer totalQuantity;
    private int issuedQuantity;
    private int discountAmount;
    private int minAvailableAmount;
    private LocalDateTime dateIssueStart;
    private LocalDateTime dateIssueEnd;

    public boolean issuableCoupon() {
        return availableIssueDate() && availableIssueQuantity();
    }

    public boolean availableIssueQuantity() {
        if (totalQuantity == null) {
            return true;
        }
        return totalQuantity > issuedQuantity;
    }

    public boolean availableIssueDate() {
        LocalDateTime now = LocalDateTime.now();
        return dateIssueStart.isBefore(now) && dateIssueEnd.isAfter(now);
    }

    public boolean isIssueComplete() {
        LocalDateTime now = LocalDateTime.now();
        return dateIssueEnd.isBefore(now) || !availableIssueQuantity();
    }

    public void issue() {
        if (!availableIssueQuantity()) {
            throw new RuntimeException("발급 가능 수량 초과!!");
        }
        if (!availableIssueDate()) {
            throw new RuntimeException("발급 가능한 일자가 아닙니다!");
        }
        issuedQuantity++;
    }
}
