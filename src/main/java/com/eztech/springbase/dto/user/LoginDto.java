package com.eztech.springbase.dto.user;

import com.eztech.springbase.dto.BaseDto;
import com.eztech.springbase.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;

/**
 * @author CQR
 */
@Data
@ApiModel("登录表单")
@EqualsAndHashCode(callSuper = false)
public class LoginDto extends BaseDto<User> {

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

    /**
     * 构造实体
     *
     * @return 实体对象
     */
    @Override
    public User buildEntity() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
