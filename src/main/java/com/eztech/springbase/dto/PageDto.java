package com.eztech.springbase.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;


/**
 * 分页数据
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "分页数据", description = "需要分页的页码和数量")
public abstract class PageDto extends SortDto {

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码 从第一页开始 1", example = "1")
    @Min(value = 1, message = "页码输入有误")
    private Integer page = 1;

    /**
     * 每页显示的数量
     */
    @ApiModelProperty(value = "每页显示的数量 范围在1~100", example = "10")
    @Range(min = 1, max = 100, message = "每页显示的数量输入有误")
    private Integer size = 10;

}

