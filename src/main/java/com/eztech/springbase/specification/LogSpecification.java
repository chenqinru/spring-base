package com.eztech.springbase.specification;

import com.eztech.springbase.entity.Log;
import org.springframework.data.jpa.domain.Specification;

/**
 * 日志规范
 *
 * @author chenqinru
 * @date 2023/07/23
 */
public class LogSpecification extends BaseSpecification {

    public static Specification<Log> withUsername(String username) {
        return (root, query, builder) -> username == null ? null : builder.equal(root.get("username"), username);
    }

    public static Specification<Log> withNickname(String nickname) {
        return (root, query, builder) -> nickname == null ? null : builder.equal(root.get("nickname"), nickname);
    }

    public static Specification<Log> withType(String type) {
        return (root, query, builder) -> type == null ? null : builder.equal(root.get("type"), type);
    }

    public static Specification<Log> withMethod(String method) {
        return (root, query, builder) -> method == null ? null : builder.equal(root.get("method"), method);
    }

    public static Specification<Log> withPath(String path) {
        return (root, query, builder) -> path == null ? null : builder.like(root.get("path"), "%" + path + "%");
    }
}
