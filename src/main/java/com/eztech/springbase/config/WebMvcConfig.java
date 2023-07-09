package com.eztech.springbase.config;

import org.jetbrains.annotations.NotNull;
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
    //@Bean
    //public LogInterceptor logInterceptor() {
    //    return new LogInterceptor();
    //}

    //@Bean
    //public FilterRegistrationBean<RequestFilter> httpServletRequestReplacedFilter() {
    //    FilterRegistrationBean<RequestFilter> registration = new FilterRegistrationBean<>(new RequestFilter());
    //    // /* 是全部的请求拦截，和Interceptor的拦截地址/**区别开
    //    registration.addUrlPatterns("/*");
    //    registration.setName("requestFilter");
    //    registration.setOrder(1);
    //    return registration;
    //}

    //实现拦截器 要拦截的路径以及不拦截的路径
    @Override
    public void addInterceptors(@NotNull InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        //registry.addInterceptor(logInterceptor()).addPathPatterns("/**")
        //        .excludePathPatterns("/swagger-ui.html")
        //        .excludePathPatterns("/swagger-resources/**")
        //        .excludePathPatterns("/doc.html")
        //        .excludePathPatterns("/v2/api-docs")
        //        .excludePathPatterns("/druid/**")
        //        .excludePathPatterns("/webjars/**")
        //        .excludePathPatterns("/favicon.ico");
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