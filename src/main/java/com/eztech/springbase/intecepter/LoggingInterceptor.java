

package com.eztech.springbase.intecepter;

import com.eztech.springbase.enums.LogTypeEnums;
import com.eztech.springbase.service.impl.LogServiceImpl;
import com.eztech.springbase.utils.JacksonUtils;
import com.eztech.springbase.utils.RequestUtils;
import com.eztech.springbase.utils.ResponseUtils;
import com.eztech.springbase.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 日志记录拦截器
 *
 * @author chenqinru
 * @date 2023/08/18
 */
@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {
    private static final ThreadLocal<Long> TIMER = new ThreadLocal<>();

    private static final String TRACE_ID = "TRACE_ID";

    @Resource
    private LogServiceImpl logService;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        if (!isController(handler)) {
            return true;
        }
        //链路ID
        MDC.put(TRACE_ID, UUID.randomUUID().toString().replace("-", ""));
        TIMER.set(System.currentTimeMillis());
        String requestBody = JacksonUtils.toJson(RequestUtils.getAllParameters(request));
        logService.add(LogTypeEnums.INFO, requestBody);
        log.info("=====> {} {} request({})", request.getMethod(), request.getRequestURI(), requestBody);
        return true;
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) {
        if (isController(handler)) {
            long responseTime = System.currentTimeMillis() - TIMER.get();
            String responseBody = ResponseUtils.getBodyAsString(response);
            responseBody = StringUtils.truncate(responseBody, 1000);
            log.info("<===== {} {}ms response({})", response.getStatus(), responseTime, responseBody);
            MDC.remove(TRACE_ID);
            TIMER.remove();
        }
    }

    /**
     * 判断访问的是不是控制器Controller
     *
     * @param handler 处理程序
     * @return boolean
     */
    private boolean isController(Object handler) {
        return handler instanceof HandlerMethod;
    }
}
