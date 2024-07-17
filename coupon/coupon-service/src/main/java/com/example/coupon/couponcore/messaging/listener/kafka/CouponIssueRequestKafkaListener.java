package com.example.coupon.couponcore.messaging.listener.kafka;

import com.example.coupon.couponcore.infrastructure.model.CouponIssueAvroModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class CouponIssueRequestKafkaListener {

    @KafkaListener(topics = "coupon_request", groupId = "spring")
    public void consumer(@Payload CouponIssueAvroModel messages) {

    }
}
