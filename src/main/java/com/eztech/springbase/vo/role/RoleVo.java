package com.eztech.springbase.vo.role;

import com.eztech.springbase.vo.permission.PermissionVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * 角色详情
 *
 * @author chenqinru
 * @date 2023/07/22
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
     * 权重
     */
    @ApiModelProperty("权重")
    private Integer weight;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 关联权限
     */
    @ApiModelProperty("关联权限")
    private List<PermissionVo> permissions = new ArrayList<>();
}
