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
public @interface GuavaRateLimiter {
    /**
     * 资源的key,唯一
     * 作用：不同的接口，不同的流量控制
     */
    String key() default "";

    /**
     * 最多的访问限制次数
     */
    double limit() default 10;

    /**
     * 获取令牌的周期（秒）
     * 假设limit=1，duration=60，计算公式为limit/duration，也就是每秒生成0.01666667个令牌
     */
    long duration() default 60;
}
