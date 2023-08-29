package com.eztech.springbase.utils;

import com.eztech.springbase.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

/**
 * Security 工具类
 *
 * @author chenqinru
 * @date 2023/07/29
 */
public final class SecurityUtils {
    private SecurityUtils() {
    }

    /**
     * 管理员标识
     */
    public static final String ADMIN_ROLE_KEY = "admin";

    /**
     * 所有权限
     */
    public static final String ALL_PERMISSIONS = "*:*:*";

    /**
     * 获取当前登录用户
     */
    public static Optional<User> getLoginUser() {
        return Optional.of(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .filter(User.class::isInstance)
                .map(User.class::cast);

    }

    ///**
    // * 获取用户信息
    // *
    // * @return 用户信息
    // */
    //public static User getUser() {
    //    return getLoginUser().orElse(null);
    //}

    ///**
    // * 获取角色信息
    // *
    // * @return 角色信息
    // */
    //public static List<Role> getRoles() {
    //    return getLoginUser().getRoles();
    //}
    //
    ///**
    // * 获取权限信息
    // *
    // * @return 权限信息
    // */
    //public static List<Permission> getPermissions() {
    //    return getUserModel().getRoles().stream().flatMap(r -> r.getPermissions().stream()).distinct().collect(Collectors.toList());
    //}
    //
    ///**
    // * 判断是否为管理员
    // *
    // * @return 结果
    // */
    //public static boolean isAdmin() {
    //    return getRoles().contains(ADMIN_ROLE_KEY) && getPermissions().contains(ALL_PERMISSIONS);
    //}

    /**
     * BCryptPasswordEncoder 生成密文密码
     *
     * @param rawPassword 明文密码
     * @return 密文密码
     */
    public static String encryptPassword(String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * 验证明文密码是否与密文密码匹配
     *
     * @param rawPassword     明文密码
     * @param encodedPassword 密文密码
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
