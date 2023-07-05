package com.eztech.springbase.config;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class ValidatedConfig implements Ordered {
    @Override
    public int getOrder() {
        // 设置 @Validated 注解的执行优先级为 2
        return 2;
    }
}
