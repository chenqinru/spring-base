package com.eztech.springbase.dto.permission;

import com.eztech.springbase.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


/**
 * 权限更新表单数据
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@Data
@ApiModel("权限更新表单数据")
@EqualsAndHashCode(callSuper = false)
public class UpdatePermissionDto extends SavePermissionDto {

    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "id不能为空", groups = {UpdateGroup.class})
    private Integer id;
}
