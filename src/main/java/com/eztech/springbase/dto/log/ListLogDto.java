package com.eztech.springbase.dto.log;

import com.eztech.springbase.dto.PageDto;
import com.eztech.springbase.entity.Log;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 * 日志列表dto
 *
 * @author chenqinru
 * @date 2023/07/10
 */
@Data
@ApiModel("列表需要的query数据")
@EqualsAndHashCode(callSuper = false)
public class ListLogDto extends PageDto<Log> {

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
     * 请求方式
     */
    @ApiModelProperty("请求方式")
    private String method;

    /**
     * 构造实体
     *
     * @return 实体对象
     */
    @Override
    public Log buildEntity() {
        Log log = new Log();
        BeanUtils.copyProperties(this, log);
        return log;
    }
}
