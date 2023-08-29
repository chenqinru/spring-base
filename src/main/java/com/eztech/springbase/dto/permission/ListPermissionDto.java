package com.eztech.springbase.dto.permission;

import com.eztech.springbase.dto.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限列表查询数据
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@Data
@ApiModel("权限列表查询数据")
@EqualsAndHashCode(callSuper = false)
public class ListPermissionDto extends PageDto {

    /**
     * 权限代码
     */
    @ApiModelProperty(value = "权限代码")
    private String code;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String name;
}
