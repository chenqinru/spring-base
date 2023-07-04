package com.eztech.springbase.aop;


import com.eztech.springbase.annotation.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Slf4j
//@Component
public class TestAspect {
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void point() {
    }

    @Before("point()")
    public void beforeAdvice() {
        log.info("前置通知{}", new Date());
    }

    @Around("point()")
    public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("环绕前通知{}", new Date());
        Object result = joinPoint.proceed();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Ignore ignore = method.getAnnotation(Ignore.class);
        Class<?>[] value = ignore.value();
        log.info("注解信息{}", new Date());
        log.info("环绕后通知{}", new Date());
    }

    @AfterReturning("point()")
    public void afterReturningAdvice() {
        log.info("后置通知{}", new Date());
    }

    @After("point()")
    public void afterAdvice() {
        log.info("最终通知{}", new Date());
    }

    @AfterThrowing("point()")
    public void afterThrowingAdvice() {
        log.info("异常通知{}", new Date());
    }

}
