package com.eztech.springbase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eztech.springbase.entity.User;
import com.eztech.springbase.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //List<Role> roles = user.getRoles();
        //List<Permission> permissions = roles.stream().flatMap(r->r.getPermissions().stream()).distinct().toList();
        //user.setAuthorities(permissions);
        //封装查询对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);

        return mapper.selectOne(queryWrapper);
    }
}
