package com.eztech.springbase.service.impl;

import com.eztech.springbase.annotation.Authorize;
import com.eztech.springbase.entity.RouteNode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Spring路由服务
 *
 * @author chenqinru
 * @date 2023/07/23
 */
@Service
public class RouteService {

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    public RouteService(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    /**
     * 列表
     *
     * @return {@link List}<{@link RouteNode}>
     */
    public List<RouteNode> list() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        List<RouteNode> routeNodes = new ArrayList<>();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> info : handlerMethods.entrySet()) {
            Method method = info.getValue().getMethod();
            RouteNode routeNode = new RouteNode();
            //请求路径
            routeNode.setPath(Objects.requireNonNull(info.getKey().getPatternsCondition()).getPatterns().iterator().next());
            //控制器名称
            routeNode.setController(info.getValue().getBeanType().getSimpleName());
            //方法名称
            routeNode.setFunction(method.getName());
            //请求方式
            routeNode.setMethod(info.getKey().getMethodsCondition().getMethods());
            //获取Authorize注解中的值后赋值代码
            Optional.ofNullable(method.getAnnotation(Authorize.class)).map(Authorize::value).ifPresent(routeNode::setCode);
            //获取ApiOperation注解中的值后赋值名称
            Optional.ofNullable(method.getAnnotation(ApiOperation.class)).map(ApiOperation::value).ifPresent(routeNode::setName);
            routeNodes.add(routeNode);
        }

        return routeNodes;
    }
}
