package com.eztech.springbase.annotation;

import java.lang.annotation.*;

/**
 * 限流注解
 *
 * @author chenqinru
 * @date 2023/07/30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisRateLimiter {
    /**
     * 限流key
     */
    String key() default "redisRateLimit:";

    /**
     * 限流时间,单位秒
     */
    int expire() default 60;

    /**
     * 限流次数
     */
    int limit() default 10;
}
