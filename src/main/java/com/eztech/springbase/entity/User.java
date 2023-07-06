package com.eztech.springbase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author CQR
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
