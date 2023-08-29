package com.eztech.springbase.annotation;

import java.lang.annotation.*;

/**
 * 记录审核日志
 *
 * @author chenqinru
 * @date 2023/07/05
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.METHOD})
public @interface AuditLog {

    /**
     * 操作内容描述
     */
    String value() default "";
}
