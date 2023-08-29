package com.eztech.springbase.entity;

import lombok.Data;

import java.util.List;

@Data
public class RouteNode {
    private String path;
    private String controller;
    private String method;
    private List<RouteNode> children;
}
