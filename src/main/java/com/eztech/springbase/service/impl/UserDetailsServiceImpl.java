package com.eztech.springbase.service.impl;

import com.eztech.springbase.entity.Permission;
import com.eztech.springbase.entity.Role;
import com.eztech.springbase.entity.User;
import com.eztech.springbase.enums.ResultEnums;
import com.eztech.springbase.exception.CustomException;
import com.eztech.springbase.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 用户详细信息服务实现
 *
 * @author chenqinru
 * @date 2023/07/19
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomException(ResultEnums.GET_ERROR);
        }
        List<Role> roles = user.getRoles();
        List<Permission> permissions = roles.stream().flatMap(r -> r.getPermissions().stream()).distinct().collect(Collectors.toList());
        List<? extends GrantedAuthority> authorities = Stream.concat(roles.stream(), permissions.stream()).collect(Collectors.toList());
        user.setAuthorities(authorities);
        return user;
    }
}
