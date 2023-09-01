package com.eztech.springbase.entity;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

/**
 * 路由节点
 *
 * @author chenqinru
 * @date 2023/09/01
 */
@Data
public class RouteNode {

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 控制器
     */
    private String controller;

    /**
     * 方法名
     */
    private String function;

    /**
     * 请求方式
     */
    private Set<RequestMethod> method;

}
