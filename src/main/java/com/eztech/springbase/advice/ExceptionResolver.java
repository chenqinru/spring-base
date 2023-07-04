//package com.eztech.springbase.advice;
//
//import com.eztech.springbase.service.IOperationLogService;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class ExceptionResolver implements HandlerExceptionResolver {
//
//    @Autowired
//    private IOperationLogService logService;
//
//    @Override
//    public ModelAndView resolveException(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, Object handler, Exception ex) {
//        logService.add(ex.getMessage());
//        // 返回null表示未完全处理异常，继续让其他HandlerExceptionResolver处理
//        return null;
//    }
//}