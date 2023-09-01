package com.eztech.springbase.controller;

import com.eztech.springbase.annotation.Authorize;
import com.eztech.springbase.annotation.GuavaRateLimiter;
import com.eztech.springbase.dto.role.ListRoleDto;
import com.eztech.springbase.dto.role.SaveRoleDto;
import com.eztech.springbase.exception.CustomException;
import com.eztech.springbase.mapper.RoleMapper;
import com.eztech.springbase.service.RoleService;
import com.eztech.springbase.validator.group.CreateGroup;
import com.eztech.springbase.validator.group.UpdateGroup;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.role.RoleVo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 角色控制器
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@RestController
@Api(tags = "角色")
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 角色列表
     *
     * @param listRoleDto 角色列表dto
     * @return {@link PageVo}<{@link RoleVo}>
     */
    @GetMapping("/list")
    @ApiOperation("角色列表")
    @Authorize("admin:role:list")
    @GuavaRateLimiter
    public PageVo<RoleVo> list(@Validated ListRoleDto listRoleDto) {
        return roleService.list(listRoleDto);
    }

    /**
     * 创建角色
     *
     * @param saveRoleDto 角色dto
     */
    @PostMapping("/create")
    @ApiOperation("创建角色")
    @ApiOperationSupport(ignoreParameters = {"id"})
    @Authorize("admin:role:create")
    @GuavaRateLimiter
    public void create(@Validated({CreateGroup.class}) @RequestBody SaveRoleDto saveRoleDto) {
        roleService.save(RoleMapper.INSTANCE.saveRoleDtoToRole(saveRoleDto));
    }

    /**
     * 角色详情
     *
     * @param id id
     * @return {@link RoleVo}
     * @throws CustomException 自定义异常
     */
    @GetMapping("/{id}")
    @ApiOperation("角色详情")
    @Authorize("admin:role:read")
    @GuavaRateLimiter
    public RoleVo read(@PathVariable Integer id) throws CustomException {
        return RoleMapper.INSTANCE.roleToVo(roleService.findById(id));
    }

    /**
     * 更新角色
     *
     * @param saveRoleDto 角色dto
     */
    @PutMapping("/update")
    @ApiOperation("更新角色")
    @Authorize("admin:role:update")
    @GuavaRateLimiter
    public void update(@Validated({UpdateGroup.class}) @RequestBody SaveRoleDto saveRoleDto) {
        roleService.updateAllById(RoleMapper.INSTANCE.saveRoleDtoToRole(saveRoleDto));
    }

    /**
     * 单个或批量删除
     *
     * @param ids id列表
     */
    @DeleteMapping("/delete")
    @ApiOperation("单个或批量删除角色")
    @Authorize("admin:role:delete")
    @GuavaRateLimiter
    public void delete(@RequestBody List<Integer> ids) {
        roleService.deleteAllById(ids);
    }
}
