package com.eztech.springbase.specification;

import com.eztech.springbase.entity.Role;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;

/**
 * 角色规范
 *
 * @author chenqinru
 * @date 2023/07/23
 */
public class RoleSpecification extends BaseSpecification {

    public static Specification<Role> withCode(String code) {
        return (root, query, builder) -> code == null ? null : builder.equal(root.get("code"), code);
    }

    public static Specification<Role> withName(String name) {
        return (root, query, builder) -> name == null ? null : builder.equal(root.get("name"), name);
    }

    public static Specification<Role> withLeftJoinPermissions() {
        return (root, query, builder) -> {
            //总结：两者的主要区别在于关联实体的加载方式：
            //join 创建连接但不立即加载关联实体（延迟加载）。
            //fetch 创建连接并立即抓取关联实体，确保主实体和关联实体一起在单个查询中加载（即时加载）。
            //使用 LEFT JOIN 来查询关联的 permissions
            root.fetch("permissions", JoinType.LEFT);
            //返回 null 表示不添加任何条件，只进行 LEFT JOIN 查询
            return null;
        };
    }
}
