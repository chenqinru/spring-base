package com.eztech.springbase.vo.user;

import com.eztech.springbase.vo.role.UserRolesVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * 用户详情
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@Data
@ApiModel("用户详情")
public class UserVo {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * 生日
     */
    @ApiModelProperty("生日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    /**
     * 用户状态 0正常 1 禁用  -1 删除
     */
    @ApiModelProperty("状态")
    private Integer status;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 关联角色
     */
    @ApiModelProperty("关联角色")
    private List<UserRolesVo> roles = new ArrayList<>();
}
