package com.eztech.springbase.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author CQR
 */
@Data
public class PageVo<T> {

    /**
     * 分页数据
     */
    @ApiModelProperty(value = "分页数据")
    private List<T> records;

    /**
     * 总条数
     */
    @ApiModelProperty(value = "总条数")
    private long total;

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private long pages;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private Integer current;

    /**
     * 查询数量
     */
    @ApiModelProperty(value = "查询数量")
    private Integer size;
}
