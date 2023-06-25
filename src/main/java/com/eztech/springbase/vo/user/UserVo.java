package com.eztech.springbase.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CQR
 */
@Data
@ApiModel("用户详情")
public class UserVo {

    /**
     * 用户编号
     */
    @ApiModelProperty("用户编号")
    private Integer id;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 生日
     */
    @ApiModelProperty("生日")
    private LocalDateTime birthday;
}
