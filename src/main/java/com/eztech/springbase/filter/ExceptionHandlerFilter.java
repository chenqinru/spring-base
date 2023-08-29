package com.eztech.springbase.filter;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理程序过滤器
 * 过滤器触发的异常无法被全局异常管理器接管，
 * 将JwtAuthenticationTokenFilter抛出的异常可以被接管
 *
 * @author chenqinru
 * @date 2023/07/27
 */
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Resource(name="handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            resolver.resolveException(request, response, null, e);
        }
    }
}
