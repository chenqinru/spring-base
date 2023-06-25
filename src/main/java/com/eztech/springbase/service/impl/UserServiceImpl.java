package com.eztech.springbase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eztech.springbase.dto.user.ListUserDto;
import com.eztech.springbase.entity.User;
import com.eztech.springbase.mapper.UserMapper;
import com.eztech.springbase.service.IUserService;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.user.UserVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author CQR
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private UserMapper userMapper;
//
//    /**
//     * 添加用户
//     * @param userDto 表单数据
//     * @return true 或者 false
//     */
//    @Override
//    public boolean addUser(AddUserDto userDto) {
//        return save(userDto.buildEntity());
//    }
//
    /**
     * 获取用户列表
     * @param listUserDto 表单数据
     * @return 用户列表
     */
    @Override
    public PageVo<UserVo> listUser(ListUserDto listUserDto) {
        PageVo<UserVo> pageVo = new PageVo<UserVo>().setCurrentAndSize(listUserDto);
        pageVo.setTotal(countUser(listUserDto.getStatus()));
        pageVo.setRecords(userMapper.listUser(listUserDto.calcCurrent()));
        return pageVo;
    }
//
//    /**
//     * 删除用户
//     * @param id id
//     */
//    @Override
//    public void deleteUser(String id) {
//        // 如果删除失败抛出异常。 -- 演示而已不推荐这样干
//        if(!removeById(id)){
//            throw new CustomException(ResultEnum.DELETE_ERROR, MethodUtil.getLineInfo());
//        }
//    }
//
    /**
     * 获取用户数量
     *
     * @param status 状态
     * @return 用户数量
     */
    private long countUser(String status){
        return count(new QueryWrapper<User>().eq("status",status));
    }

}
