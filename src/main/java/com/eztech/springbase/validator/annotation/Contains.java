package com.eztech.springbase.validator.annotation;

import com.eztech.springbase.validator.ContainsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 包含验证
 *
 * @author chenqinru
 * @date 2023/07/23
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ContainsValidator.class)
public @interface Contains {
    String[] value() default {};

    String message() default "值错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
