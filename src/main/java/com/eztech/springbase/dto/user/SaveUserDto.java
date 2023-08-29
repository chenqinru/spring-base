package com.eztech.springbase.dto.user;

import com.eztech.springbase.dto.BaseDto;
import com.eztech.springbase.validator.annotation.DateFormat;
import com.eztech.springbase.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * 新增用户表单数据
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@Data
@ApiModel("新增用户表单数据")
@EqualsAndHashCode(callSuper = false)
public class SaveUserDto extends BaseDto {

    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "id不能为空", groups = {UpdateGroup.class})
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 1, max = 16, message = "用户名长度限制为1~16")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    //@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[._~!@#$^&*])[A-Za-z0-9._~!@#$^&*]{8,20}$" , message = "密码不符合规范")
    private String password;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", required = true)
    @NotEmpty(message = "昵称不能为为空")
    @Length(min = 1, max = 10, message = "昵称长度限制为1~10")
    private String nickname;

    /**
     * 生日
     */
    @ApiModelProperty("生日")
    @Past(message = "生日时间必须小于当前时间")
    @DateFormat(message = "日期格式错误，应为 yyyy-MM-dd")
    private LocalDate birthday;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "状态", required = true)
    @NotNull(message = "状态不能为为空")
    private Integer status;

    /**
     * 角色
     */
    @ApiModelProperty(value = "角色", required = true)
    private List<Integer> roles = new ArrayList<>();
}
