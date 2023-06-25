package com.eztech.springbase.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

/**
 * @author CQR
 */
@Data
@ApiModel(value = "分页数据", description = "分页需要的表单数据")
public class PageDto<T extends PageDto<?>>{

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码 从第一页开始 1")
    @Min(value = 1, message = "页码输入有误")
    private Integer current = 1;

    /**
     * 每页显示的数量
     */
    @ApiModelProperty(value = "每页显示的数量 范围在1~100")
    @Range(min = 1, max = 100, message = "每页显示的数量输入有误")
    private Integer size = 10;

    /**
     * 计算当前页 ,方便mysql 进行分页查询
     * @return 返回 pageForm
     */
    @ApiModelProperty(hidden = true)
    public T calcCurrent(){
        current = (current - 1 ) * size;
        return (T) this;
    }
}

