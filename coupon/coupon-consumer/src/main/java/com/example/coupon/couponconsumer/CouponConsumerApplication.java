package com.example.coupon.couponconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.coupon")
public class CouponConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponConsumerApplication.class, args);
    }

}
