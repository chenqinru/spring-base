package com.eztech.springbase.utils;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Spring 工具类 在非 Spring 环境中获取 Bean
 *
 * @author Tang
 */
@Component
public final class SpringUtils implements BeanFactoryPostProcessor, ApplicationContextAware {

    /**
     * Spring Bean 工厂
     */
    private static ConfigurableListableBeanFactory beanFactory;

    /**
     * Spring 上下文
     */
    private static ApplicationContext applicationContext;

    /**
     * 设置 Spring Bean 工厂
     *
     * @param beanFactory Spring Bean 工厂
     */
    private static void setBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        SpringUtils.beanFactory = beanFactory;
    }

    /**
     * 设置 Spring 上下文
     *
     * @param applicationContext Spring 上下文
     */
    private static void setAppCtx(ApplicationContext applicationContext) {
        SpringUtils.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) {
        SpringUtils.setBeanFactory(beanFactory);
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        SpringUtils.setAppCtx(applicationContext);
    }

    /**
     * 获取 Spring Bean 工厂
     *
     * @return Spring Bean 工厂
     */
    public static ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    /**
     * 获取 Spring 上下文
     *
     * @return Spring 上下文
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    public static Object getBean(String name) {
        return beanFactory.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return beanFactory.getBean(name, requiredType);
    }


    public static Object getBean(String name, Object... args) {
        return beanFactory.getBean(name, args);
    }


    public static <T> T getBean(Class<T> requiredType) {
        return beanFactory.getBean(requiredType);
    }


    public static <T> T getBean(Class<T> requiredType, Object... args) {
        return beanFactory.getBean(requiredType, args);
    }

}
