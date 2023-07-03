package com.eztech.springbase.config;

import com.eztech.springbase.intecepter.LogInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * web mvc配置
 *
 * @author CQR
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

    //@Bean
    //@Order(1)
    //public ExceptionResolver exceptionResolver() {
    //    return new ExceptionResolver();
    //}

    //实现拦截器 要拦截的路径以及不拦截的路径
    @Override
    public void addInterceptors(@NotNull InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(logInterceptor()).addPathPatterns("/**");
        //registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/doc.html").excludePathPatterns("/webjars/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置拦截器访问静态资源
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}