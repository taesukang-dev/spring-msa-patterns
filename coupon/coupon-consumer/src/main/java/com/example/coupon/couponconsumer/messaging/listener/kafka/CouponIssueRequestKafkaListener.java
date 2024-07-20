package com.example.coupon.couponconsumer.messaging.listener.kafka;

import com.example.coupon.couponcommon.infrastructure.model.CouponIssueAvroModel;
import com.example.coupon.couponconsumer.application.ports.input.message.listener.IssueRequestMessageListener;
import com.example.coupon.couponconsumer.messaging.listener.mapper.IssueRequestMessagingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.example.coupon.couponcommon.infrastructure.kafka.KafkaConstants.COUPON_ISSUE_TOPIC;


@RequiredArgsConstructor
@Component
public class CouponIssueRequestKafkaListener {

    private final IssueRequestMessageListener issueRequestMessageListener;
    private final IssueRequestMessagingMapper mapper;

    @KafkaListener(topics = COUPON_ISSUE_TOPIC, groupId = "spring")
    public void consumer(@Payload CouponIssueAvroModel messages) {
        issueRequestMessageListener.completeIssueRequest(
                mapper.issueRequestAvroModelToIssueRequest(messages)
        );
    }
}
