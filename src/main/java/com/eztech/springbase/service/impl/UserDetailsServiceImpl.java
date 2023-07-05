package com.eztech.springbase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eztech.springbase.entity.User;
import com.eztech.springbase.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private IUserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //List<Role> roles = user.getRoles();
        //List<Permission> permissions = roles.stream().flatMap(r->r.getPermissions().stream()).distinct().toList();
        //user.setAuthorities(permissions);
        //封装查询对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);

        return userService.getOne(queryWrapper);
    }
}
