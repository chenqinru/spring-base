package com.eztech.springbase.service.impl;

import com.eztech.springbase.entity.Route;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 获取SpringBoot路由映射关系
 *
 * @author chenqinru
 * @date 2023/07/23
 */
@Service
public class RouteService {
    @Resource
    private WebApplicationContext applicationContext;

    /**
     * 获取所有路由映射关系
     *
     * @return {@link List}<{@link Route}>
     */
    public List<Route> getAllRouteMapping() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);

        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        List<Route> list = new ArrayList<>();

        // 遍历
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : map.entrySet()) {
            Route route = this.getRoute(entry);
            list.add(route);
        }

        return list;
    }

    /**
     * 获取路由对象
     *
     * @param entry 条目
     * @return {@link Route}
     */
    public Route getRoute(Map.Entry<RequestMappingInfo, HandlerMethod> entry) {
        Route route = new Route();

        RequestMappingInfo info = entry.getKey();

        // 请求路径
        PathPatternsRequestCondition requestCondition = info.getPathPatternsCondition();

        if (requestCondition != null) {
            Set<PathPattern> patterns = requestCondition.getPatterns();
            route.setPatterns(patterns
                    .stream()
                    .map(PathPattern::getPatternString)
                    .collect(Collectors.toSet()));
        }

        // 请求方法
        RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
        route.setMethods(methodsCondition.getMethods());

        HandlerMethod method = entry.getValue();
        // 类名
        route.setClassName(method.getMethod().getDeclaringClass().getName());
        // 方法名
        route.setMethodName(method.getMethod().getName());

        return route;
    }
}
