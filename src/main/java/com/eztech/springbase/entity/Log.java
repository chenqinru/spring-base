package com.eztech.springbase.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 日志实体
 *
 * @author chenqinru
 * @date 2023/07/02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "ez_log")
public class Log extends BaseEntity {

    /**
     * 日志类型
     */
    @Column
    private String type;

    /**
     * 账号
     */
    @Column
    private String username;

    /**
     * 昵称
     */
    @Column
    private String nickname;

    /**
     * 路径
     */
    @Column
    private String path;

    /**
     * 请求方式
     */
    @Column
    private String method;

    /**
     * 信息
     */
    @Column
    private String msg;

    /**
     * IP地址
     */
    @Column
    private String ip;

}
