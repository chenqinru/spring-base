package com.eztech.springbase.service;

import com.eztech.springbase.dto.role.ListRoleDto;
import com.eztech.springbase.entity.Role;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.role.RoleVo;
import org.springframework.data.domain.Page;

/**
 * 角色服务
 *
 * @author chenqinru
 * @date 2023/07/19
 */
public interface RoleService extends BaseService<Role> {
    /**
     * 根据id更新，不会触发级联的更新或删除
     *
     * @param entity 实体
     */
    void updateById(Role entity);

    /**
     * 列表
     *
     * @param listRoleDto 角色列表dto
     * @return {@link PageVo}<{@link RoleVo}>
     */
    PageVo<RoleVo> list(ListRoleDto listRoleDto);

    Page<Role> test();
}
