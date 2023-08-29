package com.eztech.springbase.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 排序
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "排序", description = "需要排序的字段")
public abstract class SortDto extends BaseDto {

    /**
     * 是否正序
     */
    @ApiModelProperty(value = "排序", example = "id desc")
    private String sort;
}

