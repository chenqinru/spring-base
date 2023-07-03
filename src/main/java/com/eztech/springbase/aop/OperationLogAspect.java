package com.eztech.springbase.aop;

import com.alibaba.fastjson.JSON;
import com.eztech.springbase.annotation.OperationLogging;
import com.eztech.springbase.entity.OperationLog;
import com.eztech.springbase.service.impl.OperationLogServiceImpl;
import com.eztech.springbase.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Aspect
//@Component
@Slf4j
public class OperationLogAspect {

    @Resource
    private OperationLogServiceImpl operationLogService;

    private final ThreadLocal<LocalDateTime> startTime = new ThreadLocal<>();

    @Before("@annotation(operationLogging)")
    public void before(OperationLogging operationLogging) {
        startTime.set(LocalDateTime.now());
    }

    @AfterReturning(pointcut = "@annotation(operationLogging)", returning = "result")
    public void afterReturning(JoinPoint joinPoint, OperationLogging operationLogging, Object result) {
        String resultStr = JSON.toJSONString(result);
        buildAndSaveLog(joinPoint, operationLogging, resultStr);
    }

    @AfterThrowing(pointcut = "@annotation(operationLogging)", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, OperationLogging operationLogging, Exception e) {
        String resultStr = e.getMessage();
        // 保留前2000个字符
        if (resultStr.length() > 2000) {
            resultStr = resultStr.substring(0, 2000);
        }
        buildAndSaveLog(joinPoint, operationLogging, resultStr);
    }

    @Transactional(rollbackFor = Exception.class)
    public void buildAndSaveLog(JoinPoint joinPoint, OperationLogging operationLogging, String resultStr) {
        OperationLog operationLog = new OperationLog();
        Optional<HttpServletRequest> request = HttpUtils.getRequest();
        //请求描述
        operationLog.setDescription(operationLogging.description());
        //请求ip
        request.map(HttpUtils::getIpAddress).ifPresent(operationLog::setIp);
        //请求方法
        request.map(HttpServletRequest::getMethod).ifPresent(operationLog::setMethod);
        //请求地址
        request.map(HttpServletRequest::getRequestURI).ifPresent(operationLog::setPath);
        //请求函数
        operationLog.setFunc(joinPoint.getSignature().getName());
        //请求账号
        operationLog.setAccount("admin");
        //请求昵称
        operationLog.setNickname("admin");
        //请求参数
        operationLog.setParams(JSON.toJSONString(joinPoint.getArgs()));
        //响应参数
        operationLog.setResult(resultStr);
        //请求耗时
        operationLog.setDuration(Duration.between(startTime.get(), LocalDateTime.now()).toMillis());
        //operationLog.setUsername(SecurityUtils.getLoginUsername());
        //operationLogService.save(operationLog);

        log.info("{}", JSON.toJSONString(operationLog));
    }
}
