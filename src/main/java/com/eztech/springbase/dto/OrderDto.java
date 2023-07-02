package com.eztech.springbase.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author CQR
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "排序", description = "需要排序的字段")
public abstract class OrderDto<T> extends BaseDto<T> {

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段", example = "id")
    private String column = "id";

    /**
     * 是否正序
     */
    @ApiModelProperty(value = "是否正序", example = "0")
    private Boolean isAsc = false;
}

