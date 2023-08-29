package com.eztech.springbase.mapper;

import com.eztech.springbase.dto.permission.ListPermissionDto;
import com.eztech.springbase.dto.permission.SavePermissionDto;
import com.eztech.springbase.entity.Permission;
import com.eztech.springbase.vo.permission.PermissionVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 权限映射器
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@Mapper(uses = {RoleMapper.class})
public interface PermissionMapper {

    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    PermissionVo permissionToVo(Permission permission);

    List<PermissionVo> permissionListToVo(List<Permission> permissions);

    Permission lisPermissionDtoToPermission(ListPermissionDto listPermissionDto);

    Permission savePermissionDtoToPermission(SavePermissionDto savePermissionDto);

}
