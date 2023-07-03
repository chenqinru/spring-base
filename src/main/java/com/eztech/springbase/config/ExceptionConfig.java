package com.eztech.springbase.config;

import com.eztech.springbase.advice.ExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * 异常配置
 *
 * @author CQR
 */
@Configuration
public class ExceptionConfig extends WebMvcConfigurationSupport {
    @Bean
    public ExceptionResolver exceptionResolver() {
        return new ExceptionResolver();
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        //exceptionResolvers.add(exceptionResolver());
    }
}