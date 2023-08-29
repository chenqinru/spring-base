package com.eztech.springbase.dto.user;

import com.eztech.springbase.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;


/**
 * 登录表单
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@Data
@ApiModel("登录表单")
@EqualsAndHashCode(callSuper = false)
public class LoginDto extends BaseDto {

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
