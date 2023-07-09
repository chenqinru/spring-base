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

/**
 * 日志切面
 *
 * @author chenqinru
 * @date 2023/07/09
 */
@Aspect
@Slf4j
@Component
public class LogAspect {
    @Resource
    private LogServiceImpl logService;

    /**
     * 匹配controller
     */
    @Pointcut("execution (* com.eztech.springbase.controller..*(..))")
    public void point() {
    }

    /**
     * 进入控制器之前记录请求日志
     *
     * @param joinPoint 连接点
     */
    @Before("point()")
    public void beforeController(JoinPoint joinPoint) {
        Log add = logService.add(LogTypeEnum.INFO, JSON.toJSONString(joinPoint.getArgs()));
        log.info("请求日志:{}", JSON.toJSONString(add));
    }
}
