package com.eztech.springbase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eztech.springbase.dto.user.ListUserDto;
import com.eztech.springbase.entity.User;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.user.UserVo;

/**
 * @author CQR
 */
public interface IUserService extends IService<User> {

//    /**
//     * 添加用户
//     * @param userDto 表单数据
//     * @return true 或者 false
//     */
//    boolean addUser(AddUserDto userDto);
//
    /**
     * 获取用户列表
     * @param listUserDto 表单数据
     * @return 用户列表
     */
    PageVo<UserVo> listUser(ListUserDto listUserDto);
//
//    /**
//     * 删除用户
//     * @param id id
//     */
//    void deleteUser(String id);

}
