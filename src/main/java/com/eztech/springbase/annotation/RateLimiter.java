package com.eztech.springbase.annotation;

import com.eztech.springbase.utils.throttle.RateLimiterAlgorithm;
import com.eztech.springbase.utils.throttle.TokenBucket;

import java.lang.annotation.*;

/**
 * 限流注解
 *
 * @author chenqinru
 * @date 2023/07/30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RateLimiters.class)
@Documented
public @interface RateLimiter {

    /**
     * 资源的key,唯一
     */
    String key() default "";

    /**
     * 算法
     */
    Class<? extends RateLimiterAlgorithm> algorithm() default TokenBucket.class;

    /**
     * 最大请求数
     */
    int maxRequests() default 0;

    /**
     * 时间窗口
     */
    int timeWindow() default 0;

    /**
     * 最大容量
     */
    int capacity() default 10;

    /**
     * 每秒令牌数
     */
    double tokensPerSecond() default 10.0 / 60;
}
