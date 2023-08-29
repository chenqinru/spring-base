package com.eztech.springbase.annotation;

import java.lang.annotation.*;

/**
 * 权限验证
 *
 * @author chenqinru
 * @date 2023/07/27
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorize {
    String value();
}
