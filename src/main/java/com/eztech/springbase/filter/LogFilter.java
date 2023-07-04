package com.eztech.springbase.filter;

import com.eztech.springbase.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        // Initialization
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        //客户端请求参数值
        String requestParam;
        //如果请求是POST获取body字符串，否则GET的话用request.getQueryString()获取参数值
        if (StringUtils.equalsIgnoreCase(HttpMethod.POST.name(), httpRequest.getMethod())) {
            requestParam = RequestUtils.getBodyString(httpRequest);
        } else {
            requestParam = httpRequest.getQueryString();
        }
        log.info("Received request: {} {} {}", httpRequest.getMethod(), httpRequest.getRequestURI(), requestParam);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup
    }
}
