package com.eztech.springbase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 操作日志
 *
 * @author chenqinru
 * @date 2023/07/02
 */
@Data
@Accessors(chain = true)
public class Log implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 日志类型
     */
    private String type;

    /**
     * 账号
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 路径
     */
    private String path;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 信息
     */
    private String msg;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 创建时间
     */
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改时间 -- 修改时自动更新
     */
    @JsonProperty("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 构造VO
     *
     * @return VO对象
     */
    public <T> T buildVo(T object) {
        BeanUtils.copyProperties(this, object);
        return object;
    }

}
