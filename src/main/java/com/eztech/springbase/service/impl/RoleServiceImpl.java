package com.eztech.springbase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eztech.springbase.dto.role.ListRoleDto;
import com.eztech.springbase.entity.Role;
import com.eztech.springbase.mapper.RoleMapper;
import com.eztech.springbase.service.IRoleService;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.role.RoleVo;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CQR
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public PageVo<RoleVo> list(ListRoleDto listRoleDto) {
        //封装查询对象
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>(listRoleDto.buildEntity());
        queryWrapper.orderBy(true, listRoleDto.getIsAsc(), listRoleDto.getColumn());
        //分页查询
        Page<Role> page = page(new Page<>(listRoleDto.getCurrent(), listRoleDto.getSize()), queryWrapper);
        List<RoleVo> list = page.getRecords().stream().map(role -> role.buildVo(new RoleVo())).collect(Collectors.toList());
        //封装返回体
        PageVo<RoleVo> pageVo = new PageVo<>();
        BeanUtils.copyProperties(page, pageVo);
        pageVo.setRecords(list);

        return pageVo;
    }
}
