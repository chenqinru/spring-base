package com.eztech.springbase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "ez_permission")
@ToString(exclude = {"roles"})
public class Permission extends BaseEntity implements GrantedAuthority {

    /**
     * 代码
     */
    @Column
    private String code;

    /**
     * 名字
     */
    @Column
    private String name;

    /**
     * 父id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 关联角色
     */
    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();

    @Override
    public String getAuthority() {
        return code;
    }
}
