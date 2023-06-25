package com.eztech.springbase.intecepter;

import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 日志拦截器
 * @author CQR
 */
public class LogInterceptor implements HandlerInterceptor {

//    private static final String TRACE_ID = "TRACE_ID";
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        String tid = UUID.randomUUID().toString().replace("-", "");
//        //可以考虑让客户端传入链路ID，但需保证一定的复杂度唯一性；如果没使用默认UUID自动生成
//        if (!StringUtils.isEmpty(request.getHeader("TRACE_ID"))) {
//            tid = request.getHeader("TRACE_ID");
//        }
//        MDC.put(TRACE_ID, tid);
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
//                                @Nullable Exception ex) {
//        MDC.remove(TRACE_ID);
//    }

}