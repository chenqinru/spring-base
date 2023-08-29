package com.eztech.springbase.specification;

import com.eztech.springbase.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;

/**
 * 用户规范
 *
 * @author chenqinru
 * @date 2023/07/23
 */
public class UserSpecification extends BaseSpecification {

    public static Specification<User> withUsername(String username) {
        return (root, query, builder) -> username == null ? null : builder.equal(root.get("username"), username);
    }

    public static Specification<User> withNickname(String nickname) {
        return (root, query, builder) -> nickname == null ? null : builder.equal(root.get("nickname"), nickname);
    }

    public static Specification<User> withStatus(Integer status) {
        return (root, query, builder) -> status == null ? null : builder.equal(root.get("status"), status);
    }

    public static Specification<User> withLeftJoinRoles() {
        return (root, query, builder) -> {
            //总结：两者的主要区别在于关联实体的加载方式：
            //join 创建连接但不立即加载关联实体（延迟加载）。
            //fetch 创建连接并立即抓取关联实体，确保主实体和关联实体一起在单个查询中加载（即时加载）。
            //使用 LEFT JOIN 来查询关联的 permissions
            root.fetch("roles", JoinType.LEFT);
            //返回 null 表示不添加任何条件，只进行 LEFT JOIN 查询
            //return query.distinct(true).getRestriction();

            return null;
        };
    }
}
