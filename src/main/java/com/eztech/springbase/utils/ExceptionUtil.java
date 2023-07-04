package com.eztech.springbase.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 异常工具类
 *
 * @author CQR
 * @date 2023/07/04
 */
@Slf4j
public class ExceptionUtil {

    /**
     * 私有化工具类 防止被实例化
     */
    private ExceptionUtil() {
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

    /**
     * 打印异常信息
     */
    public static String getMessage(Exception e) {
        String swStr = null;
        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            swStr = sw.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            //log.error(ex.getMessage());
        }
        return swStr;
    }

    /**
     * 获取完整的异常信息
     *
     * @param ex
     * @return
     */
    public static String getExceptionMessage(Exception ex) {
        // 先打印控制台
        ex.printStackTrace();
        // 在输出异常信息到日志文件
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        ex.printStackTrace(pout);
        String ret = out.toString();
        pout.close();
        try {
            out.close();
        } catch (Exception ignored) {
        }
        return ret;
    }

}
