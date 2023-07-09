package com.eztech.springbase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eztech.springbase.dto.user.ListUserDto;
import com.eztech.springbase.dto.user.LoginDto;
import com.eztech.springbase.entity.User;
import com.eztech.springbase.enums.ResultEnum;
import com.eztech.springbase.exception.CustomException;
import com.eztech.springbase.mapper.UserMapper;
import com.eztech.springbase.service.IUserService;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.user.UserVo;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CQR
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private BCryptPasswordEncoder encoder;

    @Override
    public boolean save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return super.save(user);
    }

    @Override
    public PageVo<UserVo> list(ListUserDto listUserDto) {
        //封装查询对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(listUserDto.buildEntity());
        queryWrapper.orderBy(true, listUserDto.getIsAsc(), listUserDto.getColumn());
        //分页查询
        Page<User> page = page(new Page<>(listUserDto.getCurrent(), listUserDto.getSize()), queryWrapper);
        List<UserVo> list = page.getRecords().stream().map(user -> user.buildVo(new UserVo())).collect(Collectors.toList());
        //封装返回体
        PageVo<UserVo> pageVo = new PageVo<>();
        BeanUtils.copyProperties(page, pageVo);
        pageVo.setRecords(list);

        return pageVo;
    }

    @Override
    public User login(LoginDto loginDto) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        // 使用spring security的认证方法
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(token);
        } catch (AuthenticationException e) {
            throw new CustomException(ResultEnum.GET_ERROR);
        }

        return (User) authenticate.getPrincipal();

    }
}
