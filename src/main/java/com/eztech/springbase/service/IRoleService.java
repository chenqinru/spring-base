package com.eztech.springbase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eztech.springbase.dto.role.ListRoleDto;
import com.eztech.springbase.entity.Role;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.role.RoleVo;

/**
 * @author CQR
 */
public interface IRoleService extends IService<Role> {

    /**
     * 列表
     *
     * @param listRoleDto 角色列表dto
     * @return {@link PageVo}<{@link RoleVo}>
     */
    PageVo<RoleVo> list(ListRoleDto listRoleDto);

}
