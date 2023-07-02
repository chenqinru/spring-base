package com.eztech.springbase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eztech.springbase.dto.user.ListUserDto;
import com.eztech.springbase.entity.User;
import com.eztech.springbase.mapper.UserMapper;
import com.eztech.springbase.service.IUserService;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.user.UserVo;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CQR
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
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
}
