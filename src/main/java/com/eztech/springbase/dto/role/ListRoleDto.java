package com.eztech.springbase.dto.role;

import com.eztech.springbase.dto.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenqinru
 */
@Data
@ApiModel("获取角色列表需要的query数据")
@EqualsAndHashCode(callSuper = false)
public class ListRoleDto extends PageDto {

    /**
     * 角色代码
     */
    @ApiModelProperty("角色代码")
    private String code;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String name;
}
