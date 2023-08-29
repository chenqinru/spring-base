package com.eztech.springbase.controller;

import com.eztech.springbase.dto.permission.ListPermissionDto;
import com.eztech.springbase.dto.permission.SavePermissionDto;
import com.eztech.springbase.entity.Route;
import com.eztech.springbase.exception.CustomException;
import com.eztech.springbase.mapper.PermissionMapper;
import com.eztech.springbase.service.PermissionService;
import com.eztech.springbase.service.impl.RouteService;
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

    @Resource
    private RouteService routeService;

    /**
     * 权限列表
     *
     * @param listPermissionDto 权限列表dto
     * @return {@link PageVo}<{@link PermissionVo}>
     */
    @GetMapping("/list")
    @ApiOperation("权限列表")
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
    public void delete(@RequestBody List<Integer> ids) {
        permissionService.deleteAllById(ids);
    }

    /**
     * 获取所有路由映射关系
     *
     * @return {@link List}<{@link Route}>
     */
    @GetMapping("/route")
    @ApiOperation("获取所有路由映射关系")
    public List<Route> route() {
        return routeService.getAllRouteMapping();
    }
}
