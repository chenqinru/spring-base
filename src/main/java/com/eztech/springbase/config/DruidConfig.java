//package com.eztech.springbase.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.support.http.StatViewServlet;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//
//@Configuration
//public class DruidConfig {
//
//    //导入数据源的配置
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource druidDataSource() {
//        return new DruidDataSource();
//    }
//
//    //后台监控
//    @Bean
//    public ServletRegistrationBean<StatViewServlet> monitor() {
//        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
//
//        //登录后台的账号和密码
//        HashMap<String, String> initParameters = new HashMap<>();
//        initParameters.put("loginUsername" , "wzw");
//        initParameters.put("loginPassword" , "123465");
//
//        //设置初始化参数
//        bean.setInitParameters(initParameters);
//        return bean;
//    }
//}