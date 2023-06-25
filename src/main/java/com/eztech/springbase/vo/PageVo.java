package com.eztech.springbase.vo;

import com.eztech.springbase.dto.PageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

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

    /**
     * 设置当前页和每页显示的数量
     *
     * @param pageDto 分页表单
     * @return 返回分页信息
     */
    @ApiModelProperty(hidden = true)
    public PageVo<T> setCurrentAndSize(PageDto<?> pageDto) {
        BeanUtils.copyProperties(pageDto, this);
        return this;
    }

//    /**
//     * 设置总记录数
//     *
//     * @param total 总记录数
//     */
//    @ApiModelProperty(hidden = true)
//    public void setTotal(long total) {
//        this.total = total;
//        this.setPages(this.total % this.size > 0 ? this.total / this.size + 1 : this.total / this.size);
//    }
}
