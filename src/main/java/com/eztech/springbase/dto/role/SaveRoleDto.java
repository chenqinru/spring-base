package com.eztech.springbase.dto.role;

import com.eztech.springbase.dto.BaseDto;
import com.eztech.springbase.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenqinru
 */
@Data
@ApiModel("角色写入需要的表单数据")
@EqualsAndHashCode(callSuper = false)
public class SaveRoleDto extends BaseDto {

    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "id不能为空", groups = {UpdateGroup.class})
    private Integer id;

    /**
     * 角色代码
     */
    @ApiModelProperty(value = "角色代码", required = true)
    @NotEmpty(message = "代码不能为空")
    @Length(min = 1, max = 50, message = "代码长度限制为1~50")
    private String code;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", required = true)
    @NotEmpty(message = "名称不能为空")
    @Length(min = 1, max = 50, message = "名称长度限制为1~50")
    private String name;

    /**
     * 权重
     */
    @ApiModelProperty(value = "权重", required = true)
    @NotNull(message = "权重不能为为空")
    private Integer weight;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Length(max = 200, message = "备注长度限制为200")
    private String remark;

    /**
     * 权限
     */
    @ApiModelProperty(value = "权限", required = true)
    private List<Integer> permissions = new ArrayList<>();
}
