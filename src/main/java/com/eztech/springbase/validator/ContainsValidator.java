package com.eztech.springbase.validator;

import com.eztech.springbase.validator.annotation.Contains;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * 包含验证器
 *
 * @author chenqinru
 * @date 2023/07/23
 */
@Slf4j
public class ContainsValidator implements ConstraintValidator<Contains, Integer> {
    private Contains contains;

    @Override
    public void initialize(Contains contains) {
        this.contains = contains;
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        List<String> list = Arrays.asList(this.contains.value());

        return list.contains(String.valueOf(value));
    }
}
