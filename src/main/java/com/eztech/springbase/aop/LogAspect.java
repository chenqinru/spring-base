package com.eztech.springbase.aop;

import com.alibaba.fastjson.JSON;
import com.eztech.springbase.entity.Log;
import com.eztech.springbase.enums.LogTypeEnum;
import com.eztech.springbase.service.impl.LogServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Slf4j
@Component
public class LogAspect {
    @Resource
    private LogServiceImpl logService;

    @Pointcut("execution (* com.eztech.springbase.controller..*(..))")
    public void beforeController() {
    }

    // 切点表达式：捕获带有@ExceptionHandler(MethodArgumentNotValidException.class)注解的方法
    @Pointcut("@annotation(org.springframework.web.bind.annotation.ExceptionHandler) && args(exception)")
    public void beforeException(Exception exception) {
    }

    @Before("beforeController()")
    public void handleController(JoinPoint joinPoint) {
        Log add = logService.add(LogTypeEnum.INFO, JSON.toJSONString(joinPoint.getArgs()));
        log.info("请求日志:{}" , JSON.toJSONString(add));
    }

    @Before("beforeException(exception)")
    public void handleException(JoinPoint joinPoint, Exception exception) {
        logService.add(LogTypeEnum.ERROR, exception.getLocalizedMessage().trim());
    }

    //@AfterThrowing(pointcut = "point()" , throwing = "e")
    //public void afterThrowing(Exception e) {
    //    logService.add(LogTypeEnum.ERROR, e.getLocalizedMessage().trim());
    //}
}
