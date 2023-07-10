package com.eztech.springbase.dto.role;

import com.eztech.springbase.dto.BaseDto;
import com.eztech.springbase.entity.Role;
import com.eztech.springbase.validation.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author CQR
 */
@Data
@ApiModel("角色写入需要的表单数据")
@EqualsAndHashCode(callSuper = false)
public class SaveRoleDto extends BaseDto<Role> {

    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "id不能为空", groups = {UpdateGroup.class})
    private Integer id;

    /**
     * 代码
     */
    @ApiModelProperty(value = "代码", required = true)
    @NotEmpty(message = "代码不能为空")
    @Length(min = 1, max = 50, message = "代码长度限制为1~50")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", required = true)
    @NotEmpty(message = "名称不能为空")
    @Length(min = 1, max = 50, message = "名称长度限制为1~50")
    private String name;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = true)
    @NotNull(message = "排序不能为为空")
    private Integer sort;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Length(max = 200, message = "备注长度限制为200")
    private String remark;

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
