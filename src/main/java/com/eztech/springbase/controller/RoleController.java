package com.eztech.springbase.controller;

import com.eztech.springbase.dto.role.ListRoleDto;
import com.eztech.springbase.dto.role.SaveRoleDto;
import com.eztech.springbase.enums.ResultEnum;
import com.eztech.springbase.exception.CustomException;
import com.eztech.springbase.service.IRoleService;
import com.eztech.springbase.validation.CreateGroup;
import com.eztech.springbase.validation.UpdateGroup;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.role.RoleVo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author CQR
 */
@RestController
@Api(tags = "角色")
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    /**
     * 角色列表
     *
     * @param listRoleDto 角色列表dto
     * @return {@link PageVo}<{@link RoleVo}>
     */
    @GetMapping("/list")
    @ApiOperation("角色列表")
    public PageVo<RoleVo> list(@Validated ListRoleDto listRoleDto) {
        return roleService.list(listRoleDto);
    }


    /**
     * 创建角色
     *
     * @param saveRoleDto 角色dto
     * @return {@link Boolean}
     */
    @PostMapping("/create")
    @ApiOperation("创建角色")
    @ApiOperationSupport(ignoreParameters = {"id"})
    public Boolean create(@Validated({CreateGroup.class}) @RequestBody SaveRoleDto saveRoleDto) {
        return roleService.save(saveRoleDto.buildEntity());
    }


    /**
     * 角色详情
     *
     * @param id id
     * @return {@link RoleVo}
     */
    @GetMapping("/{id}")
    @ApiOperation("角色详情")
    public RoleVo read(@PathVariable Integer id) throws CustomException {
        return Optional.ofNullable(roleService.getById(id)).orElseThrow(() -> new CustomException(ResultEnum.GET_ERROR)).buildVo(new RoleVo());
    }


    /**
     * 更新角色
     *
     * @param saveRoleDto 角色dto
     * @return {@link Boolean}
     */
    @PutMapping("/update")
    @ApiOperation("更新角色")
    public Boolean update(@Validated({UpdateGroup.class}) @RequestBody SaveRoleDto saveRoleDto) {
        return roleService.updateById(saveRoleDto.buildEntity());
    }


    /**
     * 单个或批量删除
     *
     * @param ids id列表
     * @return {@link Boolean}
     */
    @DeleteMapping("/delete")
    @ApiOperation("单个/批量删除角色")
    public Boolean delete(@RequestBody List<Integer> ids) {
        return roleService.removeByIds(ids);
    }
}
