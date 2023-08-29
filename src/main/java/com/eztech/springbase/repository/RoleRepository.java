package com.eztech.springbase.repository;

import com.eztech.springbase.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * 角色库
 *
 * @author chenqinru
 * @date 2023/07/19
 */
@Repository
public interface RoleRepository extends BaseRepository<Role> {
    // 使用 JOIN FETCH 进行多对多关联的查询
    //@Query("SELECT DISTINCT p FROM Role p JOIN FETCH p.permissions")
    //Page<Role> findAllRolesWithJoinFetch(Example<Role> example, Pageable pageable);

    //@Query(value = "SELECT DISTINCT p FROM Role p LEFT JOIN FETCH p.permissions",
    //        countQuery = "SELECT COUNT(p) FROM Role p LEFT JOIN p.permissions")
    //Page<Role> findAllRolesWithLeftJoinFetch(Example<Role> example, Pageable pageable);
}
