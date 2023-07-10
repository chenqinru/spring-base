package com.eztech.springbase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eztech.springbase.entity.Role;
import com.eztech.springbase.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author CQR
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select * from user where id = #{id}")
    Role selectByUid(Integer id);

    @Select("select * from user")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "code", property = "code"),
            @Result(column = "name", property = "name"),
            @Result(column = "id", property = "user", javaType = User.class,
                    one = @One(select = "com.eztech.springbase.mapper.UserMapper.selectById")
            ),
    })
    List<Role> selectAllRoleAndUsers();

}
