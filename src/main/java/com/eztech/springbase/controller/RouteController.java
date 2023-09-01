package com.eztech.springbase.controller;

import com.eztech.springbase.annotation.Authorize;
import com.eztech.springbase.annotation.GuavaRateLimiter;
import com.eztech.springbase.entity.RouteNode;
import com.eztech.springbase.service.impl.RouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 路由控制器
 *
 * @author chenqinru
 * @date 2023/07/23
 */
@RestController
@Api(tags = "路由")
@RequestMapping("/route")
public class RouteController {

    @Resource
    private RouteService routeService;

    /**
     * 列表
     *
     * @return {@link List}<{@link RouteNode}>
     */
    @GetMapping("/list")
    @ApiOperation("路由列表")
    @Authorize("admin:route:read")
    @GuavaRateLimiter
    public List<RouteNode> list() {
        return routeService.list();
    }
}
