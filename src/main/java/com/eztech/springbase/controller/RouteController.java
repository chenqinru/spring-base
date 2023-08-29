package com.eztech.springbase.controller;

import com.eztech.springbase.entity.RouteNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    public RouteController(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    @ApiOperation("获取所有路由映射")
    @GetMapping("/allRoutes")
    public List<RouteNode> getAllRoutes() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        List<RouteNode> routeNodes = new ArrayList<>();

        for (RequestMappingInfo info : handlerMethods.keySet()) {
            HandlerMethod handlerMethod = handlerMethods.get(info);
            String controllerName = handlerMethod.getBeanType().getSimpleName();
            String methodName = handlerMethod.getMethod().getName();
            String requestPath = Objects.requireNonNull(info.getPatternsCondition()).getPatterns().iterator().next();
            //String parent = handlerMethod.getBeanType().getAnnotation(RequestMapping.class).value();
            RouteNode routeNode = new RouteNode();
            routeNode.setPath(requestPath);
            routeNode.setController(controllerName);
            routeNode.setMethod(methodName);

            addRouteNode(routeNodes, routeNode);
        }

        return routeNodes;
    }

    private void addRouteNode(List<RouteNode> routeNodes, RouteNode newNode) {
        for (RouteNode node : routeNodes) {
            if (newNode.getPath().startsWith(node.getPath())) {
                if (node.getChildren() == null) {
                    node.setChildren(new ArrayList<>());
                }
                addRouteNode(node.getChildren(), newNode);
                return;
            }
        }

        routeNodes.add(newNode);
    }
}
