package com.eztech.springbase.controller;

import com.eztech.springbase.annotation.Authorize;
import com.eztech.springbase.annotation.RateLimiter;
import com.eztech.springbase.dto.permission.ListPermissionDto;
import com.eztech.springbase.dto.permission.SavePermissionDto;
import com.eztech.springbase.exception.CustomException;
import com.eztech.springbase.mapper.PermissionMapper;
import com.eztech.springbase.service.PermissionService;
import com.eztech.springbase.validator.group.CreateGroup;
import com.eztech.springbase.validator.group.UpdateGroup;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.permission.PermissionVo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限控制器
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@RestController
@Api(tags = "权限")
@RequestMapping("/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    /**
     * 权限列表
     *
     * @param listPermissionDto 权限列表dto
     * @return {@link PageVo}<{@link PermissionVo}>
     */
    @GetMapping("/list")
    @ApiOperation("权限列表")
    @Authorize("admin:permission:list")
    @RateLimiter
    public PageVo<PermissionVo> list(@Validated ListPermissionDto listPermissionDto) {
        return permissionService.list(listPermissionDto);
    }

    /**
     * 创建权限
     *
     * @param savePermissionDto 权限dto
     */
    @PostMapping("/create")
    @ApiOperation("创建权限")
    @ApiOperationSupport(ignoreParameters = {"id"})
    @Authorize("admin:permission:create")
    @RateLimiter
    public void create(@Validated({CreateGroup.class}) @RequestBody SavePermissionDto savePermissionDto) {
        permissionService.save(PermissionMapper.INSTANCE.savePermissionDtoToPermission(savePermissionDto));
    }

    /**
     * 权限详情
     *
     * @param id id
     * @return {@link PermissionVo}
     * @throws CustomException 自定义异常
     */
    @GetMapping("/{id}")
    @ApiOperation("权限详情")
    @Authorize("admin:permission:read")
    @RateLimiter
    public PermissionVo read(@PathVariable Integer id) throws CustomException {
        return PermissionMapper.INSTANCE.permissionToVo(permissionService.findById(id));
    }

    /**
     * 更新权限
     *
     * @param savePermissionDto 权限dto
     */
    @PutMapping("/update")
    @ApiOperation("更新权限")
    @Authorize("admin:permission:update")
    @RateLimiter
    public void update(@Validated({UpdateGroup.class}) @RequestBody SavePermissionDto savePermissionDto) {
        permissionService.updateAllById(PermissionMapper.INSTANCE.savePermissionDtoToPermission(savePermissionDto));
    }

    /**
     * 单个或批量删除
     *
     * @param ids id列表
     */
    @DeleteMapping("/delete")
    @ApiOperation("单个或批量删除权限")
    @Authorize("admin:permission:delete")
    @RateLimiter
    public void delete(@RequestBody List<Integer> ids) {
        permissionService.deleteAllById(ids);
    }
}
