//package com.eztech.springbase.advice;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.HttpInputMessage;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.util.StreamUtils;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//import java.nio.charset.StandardCharsets;
//
//@Slf4j
//@RestControllerAdvice
//public class RequestLoggingBodyAdvice implements RequestBodyAdvice {
//    @Override
//    public boolean supports(MethodParameter methodParameter, Type type,
//                            Class<? extends HttpMessageConverter<?>> aClass) {
//        // 返回true表示对所有请求的请求体进行处理
//        return true;
//    }
//
//    @Override
//    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage,
//                                  MethodParameter methodParameter, Type targetType,
//                                  Class<? extends HttpMessageConverter<?>> converterType) {
//        // 请求体为空时的处理逻辑
//        log.debug("Received empty request body");
//        return body;
//    }
//
//    @Override
//    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter methodParameter,
//                                           Type targetType, Class<? extends HttpMessageConverter<?>> converterType)
//            throws IOException {
//        // 读取请求体前的处理逻辑
//        logRequestDetails(inputMessage);
//        return inputMessage;
//    }
//
//    @Override
//    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter methodParameter,
//                                Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
//        // 读取请求体后的处理逻辑
//        try {
//            logRequestDetails(inputMessage);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return body;
//    }
//
//    private void logRequestDetails(HttpInputMessage inputMessage) throws IOException {
//        // 记录请求体的日志信息
//        String requestBody = StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.UTF_8);
//        log.debug("Request Body: {}", requestBody);
//    }
//
//}
