package com.eztech.springbase.service;

import com.eztech.springbase.dto.permission.ListPermissionDto;
import com.eztech.springbase.entity.Permission;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.permission.PermissionVo;


/**
 * 权限服务
 *
 * @author chenqinru
 * @date 2023/07/22
 */
public interface PermissionService extends BaseService<Permission> {
    /**
     * 列表
     *
     * @param listPermissionDto 列表允许dto
     * @return {@link PageVo}<{@link PermissionVo}>
     */
    PageVo<PermissionVo> list(ListPermissionDto listPermissionDto);
}
