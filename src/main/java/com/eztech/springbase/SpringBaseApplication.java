package com.eztech.springbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableAspectJAutoProxy
//@ServletComponentScan
public class SpringBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBaseApplication.class, args);
    }

}
