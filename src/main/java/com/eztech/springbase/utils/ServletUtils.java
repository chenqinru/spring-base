package com.eztech.springbase.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Servlet 工具类
 *
 * @author chenqinru
 */
public class ServletUtils {

    private ServletUtils() {
    }

    /**
     * 获取请求属性
     *
     * @return {@link ServletRequestAttributes}
     */
    public static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    }

    /**
     * 获取请求对象
     *
     * @return {@link HttpServletRequest}
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取请求对象
     *
     * @return {@link Optional}<{@link HttpServletRequest}>
     */
    public static Optional<HttpServletRequest> getRequestAsOptional() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(requestAttributes -> ((ServletRequestAttributes) requestAttributes).getRequest());
    }

    /**
     * 获取响应对象
     *
     * @return {@link HttpServletResponse}
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取Session对象
     *
     * @return {@link HttpSession}
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }
}
