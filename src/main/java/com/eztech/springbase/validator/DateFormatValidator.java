package com.eztech.springbase.validator;

import com.eztech.springbase.validator.annotation.DateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 日期格式验证器
 *
 * @author chenqinru
 * @date 2023/07/21
 */
public class DateFormatValidator implements ConstraintValidator<DateFormat, Object> {
    private DateFormat dateFormat;

    @Override
    public void initialize(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        // 如果 value 为空则不进行格式验证，为空验证可以使用 @NotBlank @NotNull @NotEmpty 等注解来进行控制，职责分离
        if (value == null) {
            return true;
        }

        String format = dateFormat.format();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        try {
            LocalDate.parse(value.toString(), formatter);
            //simpleDateFormat.parse(value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}