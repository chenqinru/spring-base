package com.eztech.springbase.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author CQR
 */
@Slf4j
public class MethodUtil {

    /**
     * 私有化工具类 防止被实例化
     */
    private MethodUtil() {
    }

    public static String getLineInfo() {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        return ste.getFileName() + " -> " + ste.getLineNumber() + "行";
    }

}
