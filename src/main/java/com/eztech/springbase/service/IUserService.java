package com.eztech.springbase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eztech.springbase.dto.user.ListUserDto;
import com.eztech.springbase.dto.user.LoginDto;
import com.eztech.springbase.entity.User;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.user.UserVo;

/**
 * @author CQR
 */
public interface IUserService extends IService<User> {

    /**
     * 列表
     *
     * @param listUserDto 用户列表dto
     * @return {@link PageVo}<{@link UserVo}>
     */
    PageVo<UserVo> list(ListUserDto listUserDto);

    /**
     * 登录
     *
     * @param loginDto 登录dto
     * @return {@link User}
     */
    User login(LoginDto loginDto);

}
