package com.example.coupon.couponconsumer.messaging.listener.kafka;

import com.example.coupon.couponcommon.infrastructure.model.CouponIssueAvroModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.example.coupon.couponcommon.infrastructure.kafka.KafkaConstants.COUPON_ISSUE_TOPIC;


@Component
public class CouponIssueRequestKafkaListener {

    @KafkaListener(topics = COUPON_ISSUE_TOPIC, groupId = "spring")
    public void consumer(@Payload CouponIssueAvroModel messages) {
        System.out.println("==============");
        System.out.println(messages.getUserId());
        System.out.println(messages.getCouponId());
        System.out.println("==============");
    }
}
