package com.eztech.springbase.aspect;

import com.eztech.springbase.annotation.RateLimiter;
import com.eztech.springbase.annotation.RateLimiters;
import com.eztech.springbase.enums.ResultEnums;
import com.eztech.springbase.exception.RateLimiterException;
import com.eztech.springbase.utils.JwtContextUtils;
import com.eztech.springbase.utils.throttle.RateLimiterAlgorithm;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 限流切面
 *
 * @author chenqinru
 * @date 2023/07/30
 */
@Aspect
@Component
@Slf4j
public class RateLimiterAspect {

    private final Map<String, RateLimiterAlgorithm> rateLimiterMap = Maps.newConcurrentMap();

    @Before("@annotation(com.eztech.springbase.annotation.RateLimiters) || @annotation(com.eztech.springbase.annotation.RateLimiter)")
    public void doBefore(JoinPoint point) throws InstantiationException, IllegalAccessException {
        List<RateLimiter> rateLimiters = getAnnotation(point);
        for (RateLimiter rateLimiter : rateLimiters) {
            String combineKey = getCombineKey(rateLimiter, point);
            RateLimiterAlgorithm algorithm = rateLimiter.algorithm().newInstance().create(rateLimiter);
            RateLimiterAlgorithm tokenBucket = rateLimiterMap.computeIfAbsent(combineKey, key -> algorithm);
            if (!tokenBucket.tryAcquire()) {
                throw new RateLimiterException(ResultEnums.TOO_MANY_REQUESTS);
            }
        }
    }

    private List<RateLimiter> getAnnotation(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        List<RateLimiter> list = new LinkedList<>();
        RateLimiters rateLimiters = signature.getMethod().getAnnotation(RateLimiters.class);
        if (rateLimiters != null) {
            list.addAll(Arrays.asList(rateLimiters.value()));
        }
        RateLimiter rateLimiter = signature.getMethod().getAnnotation(RateLimiter.class);
        if (rateLimiter != null) {
            list.add(rateLimiter);
        }

        return list;
    }

    private String getCombineKey(RateLimiter rateLimiter, JoinPoint point) {
        Object[] args = point.getArgs();
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        String key = parseKey(rateLimiter.key(), method, args);
        StringBuilder stringBuffer = new StringBuilder(key);
        String algorithmName = rateLimiter.algorithm().getSimpleName();
        String controllerName = point.getSignature().getDeclaringTypeName();
        String methodName = point.getSignature().getName();
        //组合key
        return stringBuffer.append(algorithmName).append("-")
                .append(controllerName).append("-")
                .append(methodName).append("-")
                .append(JwtContextUtils.getClaims().get("userId"))
                .toString();
    }

    private String parseKey(String key, Method method, Object[] args) {
        if (!StringUtils.hasText(key)) {
            return key;
        }
        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);

        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < Objects.requireNonNull(paraNameArr).length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }
}
