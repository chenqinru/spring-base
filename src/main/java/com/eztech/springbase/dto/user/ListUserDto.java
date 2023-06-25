package com.eztech.springbase.dto.user;

import com.eztech.springbase.dto.PageDto;
import com.eztech.springbase.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;

/**
 * @author CQR
 */
@Data
@ApiModel("获取用户列表需要的表单数据")
@EqualsAndHashCode(callSuper = false)
public class ListUserDto extends PageDto<ListUserDto> {

    /**
     * 用户状态
     */
    @ApiModelProperty("用户状态")
    @Range(min = -1, max = 1, message = "用户状态有误")
    private String status;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    @Length(min = 1, max = 10, message = "昵称长度限制为1~10")
    private String nickname;

    /**
     * 构造实体
     *
     * @return 实体对象
     */
    public User buildEntity() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
