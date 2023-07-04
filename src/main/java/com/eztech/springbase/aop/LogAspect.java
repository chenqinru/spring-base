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

import javax.annotation.Resource;

@Aspect
@Slf4j
//@Component
public class LogAspect {
    @Resource
    private LogServiceImpl logService;

    @Pointcut("execution (* com.eztech.springbase.controller..*(..))")
    public void point() {
    }

    @Before("point()")
    public void before(JoinPoint joinPoint) {
        Log add = logService.add(LogTypeEnum.INFO, JSON.toJSONString(joinPoint.getArgs()));
        log.info("请求日志:{}", JSON.toJSONString(add));
    }
}
