package com.eztech.springbase.filter;

import com.eztech.springbase.wrapper.ContentCachingRequestWrapper;
import com.eztech.springbase.wrapper.ContentCachingResponseWrapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 内容缓存包装器过滤器
 *
 * @author chenqinru
 * @date 2023/08/14
 */
@Component
public class ContentCachingWrapperFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        // 使用 重写 HttpServletRequestWrapper 的自定义包装类
        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }
        // 使用 重写 HttpServletResponseWrapper 的自定义包装类
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }
        filterChain.doFilter(request, response);
        // 需要重新写入内容，否则流无输出内容
        ((ContentCachingResponseWrapper) response).copyBodyToResponse();
    }
}
