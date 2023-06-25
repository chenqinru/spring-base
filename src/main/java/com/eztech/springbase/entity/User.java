package com.eztech.springbase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author CQR
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 用户状态 0正常 1 禁用  -1 删除
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间 -- 修改时自动更新
     */
    private Date updateTime;

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
