package com.eztech.springbase.dto.permission;

import com.eztech.springbase.dto.BaseDto;
import com.eztech.springbase.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * 权限新增表单数据
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@Data
@ApiModel("权限新增表单数据")
@EqualsAndHashCode(callSuper = false)
public class SavePermissionDto extends BaseDto {

    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "id不能为空", groups = {UpdateGroup.class})
    private Integer id;

    /**
     * 权限代码
     */
    @ApiModelProperty(value = "权限代码", required = true)
    @NotEmpty(message = "权限代码不能为为空")
    @Length(min = 1, max = 50, message = "权限代码名称长度限制为1~50")
    private String code;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称", required = true)
    @NotEmpty(message = "权限名称不能为为空")
    @Length(min = 1, max = 50, message = "权限名称长度限制为1~50")
    private String name;

    /**
     * 父级主键
     */
    @ApiModelProperty(value = "父级主键", required = true)
    @NotNull(message = "父级主键不能为为空")
    private Integer parentId;
}
