package com.eztech.springbase.aspect;

import com.eztech.springbase.annotation.GuavaRateLimiter;
import com.eztech.springbase.exception.RateLimiterException;
import com.eztech.springbase.utils.ServletUtils;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 限流切面
 *
 * @author chenqinru
 * @date 2023/07/30
 */
@Aspect
@Component
@Slf4j
public class GuavaRateLimiterAspect {
    /**
     * 不同的接口，不同的流量控制
     * map的key为 Limiter.key
     */
    private final Map<String, RateLimiter> rateLimiterMap = Maps.newConcurrentMap();

    @Before("@annotation(guavaRateLimiter)")
    public void doBefore(JoinPoint point, GuavaRateLimiter guavaRateLimiter) {
        double limit = guavaRateLimiter.limit();
        long duration = guavaRateLimiter.duration();
        String combineKey = getCombineKey(guavaRateLimiter,point);
        RateLimiter rateLimiter = rateLimiterMap.computeIfAbsent(combineKey, key -> RateLimiter.create(limit / duration));
        if (!rateLimiter.tryAcquire()) {
            throw new RateLimiterException(300, "访问过于频繁，请稍候再试");
        }
    }

    private String getCombineKey(GuavaRateLimiter guavaRateLimiter,JoinPoint point) {
        //TODO 以后可以拿token中的ID作为唯一条件
        String key = guavaRateLimiter.key();
        String id;
        if (StringUtils.hasLength(key)) {
            id = key;
        } else {
            id = ServletUtils.getSession().getId();
        }
        return point.getSignature().toLongString() + "-" + id;
    }
}
