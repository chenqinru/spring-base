package com.eztech.springbase.vo.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CQR
 */
@Data
@ApiModel("日志")
public class LogVo {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Integer id;

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
     * 内容
     */
    @ApiModelProperty("内容")
    private Object msg;

    /**
     * IP
     */
    @ApiModelProperty("IP")
    private String ip;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
