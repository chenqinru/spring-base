package com.eztech.springbase.utils;

import com.eztech.springbase.wrapper.ContentCachingRequestWrapper;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求工具类
 *
 * @author chenqinru
 * @date 2023/07/03
 */
public final class RequestUtils {

    /**
     * 私有化工具类 防止被实例化
     */
    private RequestUtils() {
    }

    /**
     * 获得ip地址
     *
     * @param request 请求
     * @return {@link String}
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 包装请求
     *
     * @param request 请求
     * @return {@link HttpServletRequest}
     */
    public static HttpServletRequest wrapper(HttpServletRequest request) {
        if (!(request instanceof ContentCachingRequestWrapper)) {
            try {
                request = new ContentCachingRequestWrapper(request);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return request;
    }

    /**
     * POST获取body字符串
     *
     * @param request 请求
     * @return {@link String}
     */
    public static String getBodyAsString(HttpServletRequest request) {
        try {
            return StreamUtils.copyToString(wrapper(request).getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取query参数
     *
     * @param request 请求
     * @return {@link Map}<{@link String}, {@link String}>
     */
    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        Enumeration<String> parameters = request.getParameterNames();

        Map<String, Object> params = new HashMap<>();
        while (parameters.hasMoreElements()) {
            String parameter = parameters.nextElement();
            String value = request.getParameter(parameter);
            params.put(parameter, value);
        }

        return params;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getPathVariableMap(HttpServletRequest request) {
        return (Map<String, Object>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    }

    /**
     * 获取所有参数
     *
     * @param request 请求
     * @return {@link HashMap}<{@link Object}, {@link Object}>
     */
    public static Map<String, Object> getAllParameters(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        Map<String, Object> parameterMap = getParameterMap(request);
        Map<String, Object> variableMap = getPathVariableMap(request);
        String bodyString = getBodyAsString(request);
        Map<String, Object> bodyMap = StringUtils.hasText(bodyString) ? JacksonUtils.toMap(bodyString) : new HashMap<>();
        if (!parameterMap.isEmpty()) {
            map.putAll(parameterMap);
        }
        if (!variableMap.isEmpty()) {
            map.putAll(variableMap);
        }
        if (bodyMap != null && !bodyMap.isEmpty()) {
            map.putAll(bodyMap);
        }
        return map;
    }
}
