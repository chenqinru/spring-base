package com.eztech.springbase.dto.user;

import com.eztech.springbase.dto.BaseDto;
import com.eztech.springbase.validation.UpdateGroup;
import com.eztech.springbase.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author CQR
 */
@Data
@ApiModel("用户写入需要的表单数据")
@EqualsAndHashCode(callSuper = false)
public class SaveUserDto extends BaseDto<User> {

    @ApiModelProperty(value = "id" , required = true)
    @NotNull(message = "id不能为空" , groups = {UpdateGroup.class})
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名" , required = true)
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 1, max = 16, message = "用户名长度限制为1~16")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码" , required = true)
    @NotEmpty(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[._~!@#$^&*])[A-Za-z0-9._~!@#$^&*]{8,20}$" , message = "密码不符合规范")
    private String password;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称" , required = true)
    @NotEmpty(message = "昵称不能为为空")
    @Length(min = 1, max = 10, message = "昵称长度限制为1~10")
    private String nickname;

    /**
     * 生日
     */
    @ApiModelProperty("生日")
    @Past(message = "生日时间必须小于当前时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

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
