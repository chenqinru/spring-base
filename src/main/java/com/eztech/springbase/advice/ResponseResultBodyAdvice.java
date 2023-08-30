package com.eztech.springbase.advice;

import com.eztech.springbase.annotation.NotControllerResponseAdvice;
import com.eztech.springbase.enums.ResultEnums;
import com.eztech.springbase.exception.CustomException;
import com.eztech.springbase.utils.ResultVoUtils;
import com.eztech.springbase.vo.ResultVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Arrays;

/**
 * API接口统一JSON格式返回
 *
 * @author chenqinru
 */
@RestControllerAdvice
public class ResponseResultBodyAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 排除
     */
    private static final String[] EXCLUDE = {
            "Swagger2Controller",
            "Swagger2ControllerWebMvc",
            "ApiResourceController"
    };

    /**
     * 判断Controller方法返回值是否支持
     */
    @Override
    public boolean supports(MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果接口返回的类型本身就是Result那就没有必要进行额外的操作，返回false
        if (returnType.getGenericParameterType().equals(ResultVo.class)) {
            return false;
        }
        // 如果使用了NotControllerResponseAdvice注解，返回false
        if (returnType.hasMethodAnnotation(NotControllerResponseAdvice.class)){
            return false;
        }
        // 对类或者方法上面注解了@RestController 或者 @ResponseBody 的方法统一处理
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), RestController.class) || returnType.hasMethodAnnotation(ResponseBody.class);
    }

    /**
     * 当类或者方法使用了 @ResponseResultBody 就会调用这个方法
     */
    @Override
    public Object beforeBodyWrite(Object body,
                                  @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {
        // 防止重复包裹的问题出现
        if (body instanceof ResultVo) {
            return body;
        }
        // 避免swagger，Knife4j失效
        if (Arrays.asList(EXCLUDE).contains(returnType.getDeclaringClass().getSimpleName())) {
            return body;
        }
        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在Result里后，再转换为json字符串响应给前端
                return objectMapper.writeValueAsString(ResultVoUtils.ok(body));
            } catch (JsonProcessingException e) {
                throw new CustomException(ResultEnums.FAIL);
            }
        }

        return ResultVoUtils.ok(body);
    }
}