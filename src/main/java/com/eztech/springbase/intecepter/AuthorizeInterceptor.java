package com.eztech.springbase.intecepter;

import com.eztech.springbase.annotation.Authorize;
import com.eztech.springbase.service.impl.SecurityService;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 权限拦截器
 *
 * @author chenqinru
 * @date 2023/07/27
 */
@Component
public class AuthorizeInterceptor implements HandlerInterceptor {
    @Resource
    private SecurityService securityService;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            Authorize authorize = method.getAnnotation(Authorize.class);
            if (authorize != null) {
                boolean isAuthorized = securityService.hasPermission(authorize.value());
                if (!isAuthorized) {
                    throw new AccessDeniedException("Access is denied");
                }
            }
        }

        return true;
    }
}