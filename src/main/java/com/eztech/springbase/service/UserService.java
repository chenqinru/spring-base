package com.eztech.springbase.service;

import com.eztech.springbase.dto.user.ListUserDto;
import com.eztech.springbase.dto.user.LoginDto;
import com.eztech.springbase.entity.User;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.user.UserVo;

/**
 * 用户服务
 *
 * @author chenqinru
 * @date 2023/07/19
 */
public interface UserService extends BaseService<User> {
    /**
     * 根据id更新，不会触发级联的更新或删除
     *
     * @param entity 实体
     */
    void updateById(User entity);

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
