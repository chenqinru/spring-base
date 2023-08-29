package com.eztech.springbase.mapper;

import com.eztech.springbase.dto.role.ListRoleDto;
import com.eztech.springbase.dto.role.SaveRoleDto;
import com.eztech.springbase.entity.Permission;
import com.eztech.springbase.entity.Role;
import com.eztech.springbase.vo.role.RoleVo;
import com.eztech.springbase.vo.role.UserRolesVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色映射器
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@Mapper(uses = {UserMapper.class, PermissionMapper.class})
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleVo roleToVo(Role role);

    List<RoleVo> roleListToVo(List<Role> roles);

    UserRolesVo roleToUserRolesVo(Role role);

    List<UserRolesVo> roleListToUserRolesVo(List<Role> roles);

    Role listRoleDtoToRole(ListRoleDto listRoleDto);

    Role saveRoleDtoToRole(SaveRoleDto saveRoleDto);

    @Mapping(target = "permissions", source = "permissions")
    Role SaveRoleDtoToRole(SaveRoleDto saveRoleDto);

    @Mapping(target = "id", source = "permissionId")
    Permission permissionIdToPermission(Integer permissionId);

}
