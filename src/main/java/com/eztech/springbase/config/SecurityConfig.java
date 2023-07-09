package com.eztech.springbase.config;

import com.eztech.springbase.exception.JwtAccessDeniedHandler;
import com.eztech.springbase.exception.JwtAuthenticationEntryPoint;
import com.eztech.springbase.filter.ExceptionHandlerFilter;
import com.eztech.springbase.filter.JwtAuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import javax.annotation.Resource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig{

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Resource
    private ExceptionHandlerFilter exceptionHandlerFilter;

    @Resource
    private SecurityIgnoreUrl securityIgnoreUrl;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests((authz) -> authz.antMatchers(securityIgnoreUrl.getUrls()).permitAll().anyRequest().authenticated()).httpBasic(withDefaults());
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class).addFilterBefore(exceptionHandlerFilter, LogoutFilter.class)
        .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint()).accessDeniedHandler(new JwtAccessDeniedHandler());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

