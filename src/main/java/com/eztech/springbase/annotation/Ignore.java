package com.eztech.springbase.annotation;

import java.lang.annotation.*;

/**
 * 忽略类注解
 *
 * @author chenqinru
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.METHOD})
public @interface Ignore {

    /**
     * 忽略的类
     */
    Class<?>[] value() default {};
}
