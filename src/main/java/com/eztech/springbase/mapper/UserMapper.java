package com.eztech.springbase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eztech.springbase.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author CQR
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where id=#{id}")
    User selectById(Integer id);

    @Select("select * from user")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "password", property = "password"),
            @Result(column = "id", property = "roles", javaType = List.class,
                    many = @Many(select = "com.eztech.springbase.mapper.RoleMapper.selectByUid")
            )
    })
    List<User> selectAllUserAndRoles();
}
