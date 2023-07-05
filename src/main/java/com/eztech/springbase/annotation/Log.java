package com.eztech.springbase.annotation;

import java.lang.annotation.*;

/**
 * 记录操作日志
 *
 * @author chenqinru
 * @date 2023/07/05
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.METHOD})
public @interface Log {

    /**
     * 操作内容描述
     */
    String value() default "";
}
