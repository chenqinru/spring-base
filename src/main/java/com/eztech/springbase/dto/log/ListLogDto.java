package com.eztech.springbase.dto.log;

import com.eztech.springbase.dto.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日志列表dto
 *
 * @author chenqinru
 * @date 2023/07/10
 */
@Data
@ApiModel("列表需要的query数据")
@EqualsAndHashCode(callSuper = false)
public class ListLogDto extends PageDto {

    /**
     * 日志类型
     */
    @ApiModelProperty("日志类型")
    private String type;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * 请求方式
     */
    @ApiModelProperty("请求方式")
    private String method;

    /**
     * 请求路径
     */
    @ApiModelProperty("请求路径")
    private String path;
}
