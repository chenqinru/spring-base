package com.eztech.springbase.dto;

/**
 * @author CQR
 */
public abstract class BaseDto<T> {

    /**
     * 获取实例
     * @return 返回实体类
     */
    public abstract T buildEntity();

}
