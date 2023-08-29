package com.eztech.springbase.service.impl;

import com.eztech.springbase.dto.user.ListUserDto;
import com.eztech.springbase.dto.user.LoginDto;
import com.eztech.springbase.entity.User;
import com.eztech.springbase.enums.ResultEnums;
import com.eztech.springbase.exception.CustomException;
import com.eztech.springbase.mapper.UserMapper;
import com.eztech.springbase.repository.UserRepository;
import com.eztech.springbase.service.UserService;
import com.eztech.springbase.specification.UserSpecification;
import com.eztech.springbase.utils.SecurityUtils;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户服务实现
 *
 * @author chenqinru
 * @date 2023/07/19
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserRepository, User> implements UserService {
    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(User user) {
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        repository.save(user);
    }

    @Override
    public void updateById(User user) {
        User original = findById(user.getId());
        user.setRoles(original.getRoles());
        updateAllById(user);
    }

    @Override
    public void updateAllById(User user) {
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        repository.save(user);
    }

    @Override
    public PageVo<UserVo> list(ListUserDto listUserDto) {
        //查询条件
        Specification<User> spec = UserSpecification.combine(
                UserSpecification.withUsername(listUserDto.getUsername()),
                UserSpecification.withNickname(listUserDto.getNickname()),
                UserSpecification.withStatus(listUserDto.getStatus())
        );
        //查询
        Page<User> page = findAll(listUserDto.getPage(), listUserDto.getSize(), listUserDto.getSort(), spec);
        //列表处理
        List<UserVo> list = UserMapper.INSTANCE.userListToVo(page.getContent());
        //List<UserVo> list = page.getContent().stream().map(UserMapper.INSTANCE::userToVo).collect(Collectors.toList());
        //封装返回体
        return new PageVo<>((int) page.getTotalElements(), page.getNumber() + 1, page.getSize(), list);
    }

    @Override
    public User login(LoginDto loginDto) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        // 使用spring security的认证方法
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(token);
        } catch (AuthenticationException e) {
            throw new CustomException(ResultEnums.GET_ERROR);
        }

        User user = (User) authenticate.getPrincipal();
        if (!SecurityUtils.matchesPassword(loginDto.getPassword(), user.getPassword())) {
            throw new CustomException(0, "密码错误");
        }
        return user;
    }
}
