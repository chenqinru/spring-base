package com.eztech.springbase.utils.throttle;

import com.eztech.springbase.annotation.RateLimiter;

/**
 * 限流算法
 *
 * @author chenqinru
 * @date 2023/09/06
 */
public interface RateLimiterAlgorithm {

    /**
     * 尝试获取
     *
     * @return boolean
     */
    boolean tryAcquire();

    /**
     * 创建对象
     *
     * @param rateLimiter 限流注解
     * @return {@link RateLimiterAlgorithm}
     */
    RateLimiterAlgorithm create(RateLimiter rateLimiter);
}
