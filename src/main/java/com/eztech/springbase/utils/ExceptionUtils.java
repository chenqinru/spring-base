package com.eztech.springbase.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * 异常工具类
 *
 * @author chenqinru
 * @date 2023/07/04
 */
@Slf4j
public class ExceptionUtils {

    /**
     * 私有化工具类 防止被实例化
     */
    private ExceptionUtils() {
    }

    /**
     * 获取文件行号信息
     *
     * @return {@link String}
     */
    public static String getLineInfo(Exception e) {
        StackTraceElement ste = e.getStackTrace()[0];
        return ste.getFileName() + " -> " + ste.getLineNumber() + "行";
    }
}
