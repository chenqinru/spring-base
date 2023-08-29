package com.eztech.springbase.utils;

import com.eztech.springbase.wrapper.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletResponse;

/**
 * 响应工具类
 *
 * @author chenqinru
 * @date 2023/07/03
 */
public final class ResponseUtils {

    /**
     * 私有化工具类 防止被实例化
     */
    private ResponseUtils() {
    }

    /**
     * 包装器
     *
     * @param response 响应
     * @return {@link HttpServletResponse}
     */
    public static HttpServletResponse wrapper(HttpServletResponse response) {
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }

        return response;
    }

    /**
     * POST获取body字符串
     *
     * @param response 请求
     * @return {@link String}
     */
    public static String getBodyAsString(HttpServletResponse response) {
        ContentCachingResponseWrapper wrapper = (ContentCachingResponseWrapper) wrapper(response);

        return new String(wrapper.getContentAsByteArray());
    }
}
