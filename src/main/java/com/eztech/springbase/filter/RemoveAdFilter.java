package com.eztech.springbase.filter;

import com.alibaba.druid.util.Utils;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * * 去除druid广告拦截器
 */
@WebFilter(urlPatterns = "/druid/js/common.js")
public class RemoveAdFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String text = Utils.readFromResource("support/http/resources/js/common.js");
        text = text.replace("this.buildFooter();", "");
        response.getWriter().write(text);
    }
}