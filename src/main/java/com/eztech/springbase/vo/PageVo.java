package com.eztech.springbase.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页详情
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分页详情")
public class PageVo<T> {

    /**
     * 总数
     */
    @ApiModelProperty(value = "总数")
    private Integer total;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private Integer page;

    /**
     * 行数
     */
    @ApiModelProperty(value = "行数")
    private Integer size;

    /**
     * 分页数据
     */
    @ApiModelProperty(value = "分页数据")
    private List<T> records;

}
