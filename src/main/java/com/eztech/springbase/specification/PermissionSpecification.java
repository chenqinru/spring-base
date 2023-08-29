package com.eztech.springbase.specification;

import com.eztech.springbase.entity.Permission;
import org.springframework.data.jpa.domain.Specification;

/**
 * 权限规范
 *
 * @author chenqinru
 * @date 2023/07/23
 */
public class PermissionSpecification extends BaseSpecification {

    public static Specification<Permission> withCode(String code) {
        return (root, query, builder) -> code == null ? null : builder.equal(root.get("code"), code);
    }

    public static Specification<Permission> withName(String name) {
        return (root, query, builder) -> name == null ? null : builder.equal(root.get("name"), name);
    }
}
