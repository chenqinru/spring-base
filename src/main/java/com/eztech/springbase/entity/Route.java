package com.eztech.springbase.entity;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

/**
 * 路线
 *
 * @author chenqinru
 * @date 2023/07/23
 */
@Data
public class Route {

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 匹配规则
     */
    private Set<String> patterns;

    /**
     * 请求方式
     */
    private Set<RequestMethod> methods;

}
