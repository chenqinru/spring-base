package com.eztech.springbase.mapper;

import com.eztech.springbase.dto.user.ListUserDto;
import com.eztech.springbase.dto.user.SaveUserDto;
import com.eztech.springbase.entity.Role;
import com.eztech.springbase.entity.User;
import com.eztech.springbase.vo.user.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户映射器
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@Mapper(uses = {RoleMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserVo userToVo(User user);

    List<UserVo> userListToVo(List<User> users);

    User listUserDtoToUser(ListUserDto listUserDto);

    //@Mapping(target = "roles", ignore = true)
    //@Mapping(source = "saveUserDto.roles", target = "roles.id")
    @Mapping(target = "roles", source = "roles")
    User saveUserDtoToUser(SaveUserDto saveUserDto);

    @Mapping(target = "id", source = "roleId")
    Role roleIdToRole(Integer roleId);
}
