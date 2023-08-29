package com.eztech.springbase.vo.permission;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 权限详情
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@Data
@ApiModel("权限详情")
public class PermissionVo {

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
     * 父id
     */
    @ApiModelProperty("父id")
    private Integer parentId;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
