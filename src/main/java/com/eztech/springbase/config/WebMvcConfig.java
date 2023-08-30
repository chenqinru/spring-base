package com.eztech.springbase.config;

import com.eztech.springbase.intecepter.AuthorizeInterceptor;
import com.eztech.springbase.intecepter.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * web mvc配置
 *
 * @author chenqinru
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Resource
    private AuthorizeInterceptor authorizeInterceptor;

    @Resource
    private LoggingInterceptor loggingInterceptor;

    /**
     * 自定义拦截器
     *
     * @param registry 注册表
     */
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(authorizeInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loggingInterceptor).addPathPatterns("/**");
    }

    /**
     * 静态资源处理程序
     *
     * @param registry 注册表
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置拦截器访问静态资源
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}