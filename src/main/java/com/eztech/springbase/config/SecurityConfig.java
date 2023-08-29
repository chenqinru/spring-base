package com.eztech.springbase.config;

import com.eztech.springbase.filter.ContentCachingWrapperFilter;
import com.eztech.springbase.filter.ExceptionHandlerFilter;
import com.eztech.springbase.filter.JwtAuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.annotation.Resource;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Security安全配置
 *
 * @author chenqinru
 * @date 2023/07/25
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Resource
    private ContentCachingWrapperFilter contentCachingWrapperFilter;

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Resource
    private ExceptionHandlerFilter exceptionHandlerFilter;

    @Resource
    private LogoutHandler logoutHandler;

    @Resource
    private SecurityIgnoreUrl securityIgnoreUrl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //禁用csrf(防止跨站请求伪造攻击)
        return http.csrf().disable()
                // 启用 CORS
                .cors(withDefaults())
                //不通过Session获取SecurityContext
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //设置匹配规则
                .authorizeHttpRequests(authorize -> authorize
                        // 设置白名单
                        .antMatchers(securityIgnoreUrl.getUrls()).permitAll()
                        //除了上面的权限以外的，都必须登录才能访问
                        .anyRequest().authenticated())
                //添加JWT过滤器
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                //添加监听验证JWT异常时的过滤器
                .addFilterBefore(exceptionHandlerFilter, JwtAuthenticationTokenFilter.class)
                //添加请求响应包装器
                .addFilterBefore(contentCachingWrapperFilter, ExceptionHandlerFilter.class)
                //登出操作
                .logout(logout -> logout
                        .logoutUrl("/api/user/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //@Bean
    //public UserDetailsService userDetailsService() {
    //    //return username -> repository.findByUsername(username)
    //    //        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    //    return username -> {
    //        User user = repository.findByUsername(username);
    //        if (user == null) {
    //            throw new CustomException(ResultEnum.GET_ERROR);
    //        }
    //        List<Role> roles = user.getRoles();
    //        List<Permission> permissions = roles.stream().flatMap(r -> r.getPermissions().stream()).distinct().collect(Collectors.toList());
    //        user.setAuthorities(permissions);
    //        return user;
    //    };
    //}
    //
    //@Bean
    //public AuthenticationProvider authenticationProvider() {
    //    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    //    authProvider.setUserDetailsService(userDetailsService());
    //    authProvider.setPasswordEncoder(passwordEncoder());
    //    return authProvider;
    //}
}

