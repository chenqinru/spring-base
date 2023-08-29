package com.eztech.springbase.aspect;

import com.eztech.springbase.annotation.RedisRateLimiter;
import com.eztech.springbase.exception.RateLimiterException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * 限流处理
 *
 * @author ruoyi
 */
@Aspect
@Component
@Slf4j
public class RedisRateLimiterAspect {
    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    private DefaultRedisScript<Long> redisScript;

    @PostConstruct
    public void init() {
        redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("/static/rateLimiter.lua")));
    }


    @Before("@annotation(redisRateLimiter)")
    public void doBefore(JoinPoint point, RedisRateLimiter redisRateLimiter) {
        int expire = redisRateLimiter.expire();
        int limit = redisRateLimiter.limit();
        String combineKey = getCombineKey(redisRateLimiter, point);
        List<Object> keys = Collections.singletonList(combineKey);
        Long count = redisTemplate.execute(redisScript, keys, limit, expire);
        log.info("限制请求'{}',当前请求'{}',缓存key'{}'", limit, count, combineKey);
        if (count != null && count == 0) {
            throw new RateLimiterException(300, "访问过于频繁，请稍候再试");
        }
    }

    private String getCombineKey(RedisRateLimiter redisRateLimiter, JoinPoint point) {
        StringBuilder stringBuffer = new StringBuilder(redisRateLimiter.key());
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        stringBuffer.append(targetClass.getName()).append("-").append(method.getName());
        return stringBuffer.toString();
    }
}
