package com.eztech.springbase.intecepter;

import com.alibaba.fastjson.JSON;
import com.eztech.springbase.entity.Log;
import com.eztech.springbase.enums.LogTypeEnum;
import com.eztech.springbase.service.ILogService;
import com.eztech.springbase.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志拦截器
 * 记录请求日志和错误日志到数据库
 *
 * @author CQR
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    private ILogService logService;

    /**
     * 这个方法在业务处理器处理请求之前被调用，可以在此方法中做一些权限的校验。
     * 如果程序员决定该拦截器对请求进行拦截处理后还要调用其他的拦截器，或者是业务处理器去进行处理，则返回true；
     * 如果程序员决定不需要再调用其他的组件去处理请求，则返回false。
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理程序
     * @return boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        //JSONObject json = new JSONObject();
        //request.getParameterMap().forEach((key, value) -> json.put(key, request.getParameter(key)));
        //客户端请求参数值
        String param;
        //如果请求是POST获取body字符串，否则GET的话用request.getQueryString()获取参数值
        if (StringUtils.equalsIgnoreCase(HttpMethod.POST.name(), request.getMethod())) {
            param = RequestUtil.getBodyString(request);
        } else {
            param = request.getQueryString();
        }
        //保存请求日志到数据库
        Log operationLog = logService.add(LogTypeEnum.INFO, param);
        //记录日志
        log.info("请求日志:{}", JSON.toJSONString(operationLog));

        return true;
    }

    /**
     * 这个方法在业务处理器处理请求之后，渲染视图之前调用。在此方法中可以对ModelAndView中的模型和视图进行处理
     *
     * @param request      请求
     * @param response     响应
     * @param handler      处理程序
     * @param modelAndView 模型和视图
     * @throws Exception 异常
     */
    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, ModelAndView modelAndView) throws Exception {
        // 这里可以记录响应信息，如果需要的话
    }

    /**
     * 请求完成，视图也渲染完成后
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理程序
     * @param e        e
     * @throws Exception 异常
     */
    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception e) throws Exception {
        // 请求处理完成后的清理工作
    }

    //private static final String TRACE_ID = "TRACE_ID";
    //
    //@Override
    //public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
    //    String tid = UUID.randomUUID().toString().replace("-", "");
    //    //可以考虑让客户端传入链路ID，但需保证一定的复杂度唯一性；如果没使用默认UUID自动生成
    //    if (!StringUtils.hasLength(request.getHeader("TRACE_ID"))) {
    //        tid = request.getHeader("TRACE_ID");
    //    }
    //    MDC.put(TRACE_ID, tid);
    //    return true;
    //}
    //
    //@Override
    //public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler,
    //                            @Nullable Exception ex) {
    //    MDC.remove(TRACE_ID);
    //}

}