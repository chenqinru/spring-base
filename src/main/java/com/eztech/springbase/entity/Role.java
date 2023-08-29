package com.eztech.springbase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色实体
 *
 * @author chenqinru
 * @date 2023/07/10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "ez_role")
@ToString(exclude = {"permissions", "users"})
public class Role extends BaseEntity implements GrantedAuthority {

    /**
     * 代码
     */
    @Column
    private String code;

    /**
     * 名称
     */
    @Column
    private String name;

    /**
     * 权重
     */
    @Column
    private Integer weight;

    /**
     * 备注
     */
    @Column
    private String remark;

    /**
     * 关联用户
     */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    /**
     * 关联权限
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @BatchSize(size = 100)
    @JoinTable(
            name = "ez_role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> permissions = new ArrayList<>();

    @Override
    public String getAuthority() {
        return "ROLE_" + code;
    }
}
