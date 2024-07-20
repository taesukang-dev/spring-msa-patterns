package com.example.coupon.couponcommon.common;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableAspectJAutoProxy(exposeProxy = true)
@EnableJpaAuditing
@ComponentScan
@EnableAutoConfiguration
public class CouponConfig {
}
