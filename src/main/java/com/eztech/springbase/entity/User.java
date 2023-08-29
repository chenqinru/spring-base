package com.eztech.springbase.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户实体
 *
 * @author chenqinru
 * @date 2023/07/10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "ez_user")
@ToString(exclude = {"roles"})
public class User extends BaseEntity implements UserDetails {

    /**
     * 用户名
     */
    @Column
    private String username;

    /**
     * 密码
     */
    @Column
    private String password;

    /**
     * 昵称
     */
    @Column
    private String nickname;

    /**
     * 生日
     */
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    /**
     * 用户状态 0正常 1 禁用  -1 删除
     */
    @Column
    private Integer status;

    /**
     * 关联角色
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @BatchSize(size = 100)
    @JoinTable(
            name = "ez_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    /**
     * 授予用户的权限
     */
    @Transient
    private Collection<? extends GrantedAuthority> authorities;

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
