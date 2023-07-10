package com.eztech.springbase.dto.role;

import com.eztech.springbase.dto.PageDto;
import com.eztech.springbase.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 * @author CQR
 */
@Data
@ApiModel("获取角色列表需要的query数据")
@EqualsAndHashCode(callSuper = false)
public class ListRoleDto extends PageDto<Role> {

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
     * 构造实体
     *
     * @return 实体对象
     */
    @Override
    public Role buildEntity() {
        Role role = new Role();
        BeanUtils.copyProperties(this, role);
        return role;
    }
}
