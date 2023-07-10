package com.eztech.springbase.vo.role;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CQR
 */
@Data
@ApiModel("角色详情")
public class RoleVo {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Integer id;

    /**
     * 代码
     */
    @ApiModelProperty("代码")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 创建时间
     */
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;}
