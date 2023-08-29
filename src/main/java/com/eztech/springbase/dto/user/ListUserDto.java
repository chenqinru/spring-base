package com.eztech.springbase.dto.user;

import com.eztech.springbase.dto.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


/**
 * 用户列表查询数据
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@Data
@ApiModel("用户列表查询数据")
@EqualsAndHashCode(callSuper = false)
public class ListUserDto extends PageDto {

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @Length(min = 1, max = 10, message = "用户名长度限制为1~10")
    private String username;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    @Length(min = 1, max = 10, message = "昵称长度限制为1~10")
    private String nickname;

    /**
     * 用户状态
     */
    @ApiModelProperty("用户状态")
    @Range(min = -1, max = 1, message = "用户状态有误")
    private Integer status;
}
