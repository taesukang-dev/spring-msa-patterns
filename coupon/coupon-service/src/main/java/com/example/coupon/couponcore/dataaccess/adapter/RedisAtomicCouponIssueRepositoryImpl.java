package com.example.coupon.couponcore.dataaccess.adapter;

import com.example.coupon.couponcore.dataaccess.mapper.CouponDataAccessMapper;
import com.example.coupon.couponcore.application.ports.output.AtomicCouponIssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class RedisAtomicCouponIssueRepositoryImpl implements AtomicCouponIssueRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final CouponDataAccessMapper couponDataAccessMapper;

    @Override
    public void issueRequest(Long couponId, Long userId, int totalIssueQuantity) {
        String issueRequestKey = couponDataAccessMapper.getIssueRequestKey(couponId);

        try {
            String code = redisTemplate.execute(
                    issueRequestScript(),
                    List.of(issueRequestKey),
                    String.valueOf(userId),
                    String.valueOf(totalIssueQuantity)
            );
            assert code != null;
            checkRequestResult(code);
        } catch (Exception e) {
            throw new RuntimeException("coupon 발행 실패!! " + e.getMessage());
        }
    }

    private RedisScript<String> issueRequestScript() {
        String script = """
                if redis.call('SISMEMBER', KEYS[1], ARGV[1]) == 1 then
                    return '2'
                end
                                
                if tonumber(ARGV[2]) > redis.call('SCARD', KEYS[1]) then
                    redis.call('SADD', KEYS[1], ARGV[1])
                    return '1'
                end
                                
                return '3'
                """;
        return RedisScript.of(script, String.class);
    }

    public void checkRequestResult(String code) {
        if (code.equals("3")) {
            throw new RuntimeException("발급 가능한 수량을 초과 !!");
        }
        if (code.equals("2")) {
            throw new RuntimeException("이미 발급된 쿠폰입니다.");
        }
    }
}
