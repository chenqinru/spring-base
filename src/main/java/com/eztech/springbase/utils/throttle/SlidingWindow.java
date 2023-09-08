package com.eztech.springbase.utils.throttle;

import com.eztech.springbase.annotation.RateLimiter;
import lombok.NoArgsConstructor;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 滑动窗口算法
 *
 * @author chenqinru
 * @date 2023/09/06
 */
@NoArgsConstructor
public class SlidingWindow implements RateLimiterAlgorithm {

    private int maxRequests;
    private long timeWindow;
    private Deque<Long> requestTimestamps;

    /**
     * 滑动窗口
     *
     * @param maxRequests 最大请求数
     * @param timeWindow  时间窗口
     */
    public SlidingWindow(int maxRequests, long timeWindow) {
        this.maxRequests = maxRequests;
        this.timeWindow = timeWindow;
        this.requestTimestamps = new ArrayDeque<>();
    }

    /**
     * 尝试获取
     *
     * @return boolean
     */
    @Override
    public synchronized boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();

        // 移除过期的请求时间戳
        while (!requestTimestamps.isEmpty() && currentTime - requestTimestamps.peekFirst() > timeWindow) {
            requestTimestamps.pollFirst();
        }

        // 检查当前请求数是否超过限制
        if (requestTimestamps.size() >= maxRequests) {
            return false;
        }

        // 添加当前请求时间戳
        requestTimestamps.offerLast(currentTime);

        return true;
    }

    /**
     * 创建滑动窗口对象
     *
     * @param rateLimiter 限流注解
     * @return {@link RateLimiterAlgorithm}
     */
    @Override
    public RateLimiterAlgorithm create(RateLimiter rateLimiter) {
        return new SlidingWindow(rateLimiter.maxRequests(), rateLimiter.timeWindow());
    }
}
