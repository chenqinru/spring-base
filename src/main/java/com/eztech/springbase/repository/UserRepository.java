package com.eztech.springbase.repository;

import com.eztech.springbase.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户存储库
 *
 * @author chenqinru
 * @date 2023/07/19
 */
@Repository
public interface UserRepository extends BaseRepository<User> {

    //@NotNull
    //@Override
    //@EntityGraph(attributePaths = "roles") // 加载 user 的 roles 字段，但不加载 roles 中的 permissions 字段
    //Page<User> findAll(@Nullable Specification<User> spec, @NotNull Pageable pageable);

    //@NotNull
    //@Override
    //@EntityGraph(attributePaths = "roles")
    //Page<User> findAll(@Nullable Specification<User> spec, @NotNull Pageable pageable);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return {@link User}
     */
    User findByUsername(String username);
}
