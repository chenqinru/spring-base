package com.eztech.springbase.utils.throttle;

import com.eztech.springbase.annotation.RateLimiter;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * 令牌桶算法
 *
 * @author chenqinru
 * @date 2023/09/05
 */
@NoArgsConstructor
public class TokenBucket implements RateLimiterAlgorithm {
    private int capacity;
    private double tokensPerSecond;
    private double tokens;
    private long lastRefillTime;

    /**
     * 令牌桶
     *
     * @param capacity        最大容量
     * @param tokensPerSecond 每秒令牌数
     */
    public TokenBucket(int capacity, double tokensPerSecond) {
        this.capacity = capacity;
        this.tokensPerSecond = tokensPerSecond;
        this.tokens = capacity;
        this.lastRefillTime = System.nanoTime();
    }

    /**
     * 消费令牌
     *
     * @return boolean
     */
    @Override
    public synchronized boolean tryAcquire() {
        refillTokens();

        if (tokens > 1) {
            tokens--;
            return true;
        }

        return false;
    }

    /**
     * 创建令牌桶对象
     *
     * @param rateLimiter 限流注解
     * @return {@link RateLimiterAlgorithm}
     */
    @Override
    public RateLimiterAlgorithm create(RateLimiter rateLimiter) {
        return new TokenBucket(rateLimiter.capacity(), rateLimiter.tokensPerSecond());
    }

    /**
     * 填充令牌
     */
    private void refillTokens() {
        long currentTime = System.nanoTime();
        long elapsedTime = currentTime - lastRefillTime;
        double refillAmount = elapsedTime * tokensPerSecond / TimeUnit.SECONDS.toNanos(1);

        if (refillAmount > 0) {
            tokens = Math.min(capacity, tokens + refillAmount);
            lastRefillTime = currentTime;
        }
    }
}