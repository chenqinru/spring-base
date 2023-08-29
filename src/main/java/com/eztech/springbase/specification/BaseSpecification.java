package com.eztech.springbase.specification;

import com.eztech.springbase.entity.BaseEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础规范
 *
 * @author chenqinru
 * @date 2023/07/23
 */
public class BaseSpecification {
    /**
     * 结合查询条件
     *
     * @param specifications 规范
     * @return {@link Specification}<{@link T}>
     */
    @SafeVarargs
    public static <T extends BaseEntity> Specification<T> combine(Specification<T>... specifications) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Specification<T> specification : specifications) {
                Predicate predicate = specification.toPredicate(root, query, builder);
                if (predicate != null) {
                    predicates.add(predicate);
                }
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
