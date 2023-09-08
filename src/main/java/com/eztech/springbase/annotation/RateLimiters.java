package com.eztech.springbase.annotation;

import java.lang.annotation.*;

/**
 * 重复限流注解容器
 *
 * @author chenqinru
 * @date 2023/09/06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RateLimiters {
    RateLimiter[] value();
}
