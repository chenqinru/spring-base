package com.eztech.springbase.service.impl;

import com.eztech.springbase.constants.Role;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 安全服务
 *
 * @author chenqinru
 * @date 2023/07/26
 */
@Component("auth")
public class SecurityService {

    /**
     * 判断接口是否有xxx:xxx权限
     *
     * @param permission 权限
     * @return {boolean}
     */
    public boolean hasPermission(String permission) {
        if (StrUtil.isBlank(permission)) {
            return false;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream().map(GrantedAuthority::getAuthority).filter(StringUtils::hasText)
                .anyMatch(x -> {
                    if (Role.ADMIN.equals(x)) {
                        return true;
                    }
                    return PatternMatchUtils.simpleMatch(permission, x);
                });
    }

}
