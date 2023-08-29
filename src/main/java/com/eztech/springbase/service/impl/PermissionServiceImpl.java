package com.eztech.springbase.service.impl;

import com.eztech.springbase.dto.permission.ListPermissionDto;
import com.eztech.springbase.entity.Permission;
import com.eztech.springbase.mapper.PermissionMapper;
import com.eztech.springbase.repository.PermissionRepository;
import com.eztech.springbase.service.PermissionService;
import com.eztech.springbase.specification.PermissionSpecification;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.permission.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 权限服务实现
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@Service
@Transactional
public class PermissionServiceImpl extends BaseServiceImpl<PermissionRepository, Permission> implements PermissionService {
    @Autowired
    public PermissionServiceImpl(PermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public PageVo<PermissionVo> list(ListPermissionDto listPermissionDto) {
        //查询条件
        Specification<Permission> spec = PermissionSpecification.combine(
                PermissionSpecification.withCode(listPermissionDto.getCode()),
                PermissionSpecification.withName(listPermissionDto.getName())
        );
        //查询
        Page<Permission> page = findAll(listPermissionDto.getPage(), listPermissionDto.getSize(), listPermissionDto.getSort(), spec);
        //列表处理
        List<PermissionVo> list = PermissionMapper.INSTANCE.permissionListToVo(page.getContent());
        //封装返回体
        return new PageVo<>((int) page.getTotalElements(), page.getNumber() + 1, page.getSize(), list);
    }
}
