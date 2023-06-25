package com.eztech.springbase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eztech.springbase.entity.User;
import com.eztech.springbase.dto.user.ListUserDto;
import com.eztech.springbase.vo.user.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author CQR
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取用户列表
     * @param listUserDto 表单数据
     * @return 用户列表
     */
    @Select("SELECT id,nickname,username,birthday FROM `user` WHERE `status`= #{status} LIMIT #{current},#{size}")
    List<UserVo> listUser(ListUserDto listUserDto);

}
