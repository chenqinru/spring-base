package com.eztech.springbase.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Environment env;

    public LoggingAspect(Environment env) {
        this.env = env;
    }

    /**
     * 匹配spring框架的repositories、service、rest端点的切面
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // 方法为空，因为这只是一个切入点，实现在通知中。
    }

    /**
     * 匹配我们自己项目的repositories、service、rest端点的切面
     */
    @Pointcut("within(com.eztech.springbase.config..*)" +
            " || within(com.eztech.springbase.service..*)" +
            " || within(com.eztech.springbase.controller..*)")
    public void applicationPackagePointcut() {
        // 方法为空，因为这只是一个切入点，实现在通知中。
    }

    /**
     * 记录方法抛出异常的通知
     *
     * @param joinPoint join point for advice
     * @param e         exception
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()" , throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.info("报错");
        // 判断环境，dev、test or prod
        //if (env.acceptsProfiles(Profiles.of("dev" , "test"))) {
        //    log.error("Exception in {}.{}() with cause = '{}' and exception = '{}'" , joinPoint.getSignature().getDeclaringTypeName(),
        //            joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL" , e.getMessage(), e);
        //
        //} else {
        //    log.error("Exception in {}.{}() with cause = {}" , joinPoint.getSignature().getDeclaringTypeName(),
        //            joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
        //}

    }

    /**
     * 在方法进入和退出时记录日志的通知
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //todo
        log.info("请求");
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
        //if (log.isDebugEnabled()) {
        //    log.debug("Enter: {}.{}() with argument[s] = {}" , joinPoint.getSignature().getDeclaringTypeName(),
        //            joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        //}
        //try {
        //    Object result = joinPoint.proceed();
        //    if (log.isDebugEnabled()) {
        //        log.debug("Exit: {}.{}() with result = {}" , joinPoint.getSignature().getDeclaringTypeName(),
        //                joinPoint.getSignature().getName(), result);
        //    }
        //    return result;
        //} catch (IllegalArgumentException e) {
        //    log.error("Illegal argument: {} in {}.{}()" , Arrays.toString(joinPoint.getArgs()),
        //            joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        //
        //    throw e;
        //}

    }

}