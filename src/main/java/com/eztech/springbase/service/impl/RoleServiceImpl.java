package com.eztech.springbase.service.impl;

import com.eztech.springbase.dto.role.ListRoleDto;
import com.eztech.springbase.entity.Role;
import com.eztech.springbase.mapper.RoleMapper;
import com.eztech.springbase.repository.RoleRepository;
import com.eztech.springbase.service.RoleService;
import com.eztech.springbase.specification.BaseSpecification;
import com.eztech.springbase.specification.RoleSpecification;
import com.eztech.springbase.utils.JpaUtils;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.role.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色服务实现
 *
 * @author chenqinru
 * @date 2023/07/19
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<RoleRepository, Role> implements RoleService {
    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateById(Role role) {
        Role original = findById(role.getId());
        role.setPermissions(original.getPermissions());
        updateAllById(role);
    }

    @Override
    public PageVo<RoleVo> list(ListRoleDto listRoleDto) {
        //查询条件
        Specification<Role> spec = BaseSpecification.combine(
                RoleSpecification.withCode(listRoleDto.getCode()),
                RoleSpecification.withName(listRoleDto.getName())
        );
        //查询
        Page<Role> page = findAll(listRoleDto.getPage(), listRoleDto.getSize(), listRoleDto.getSort(), spec);
        //列表处理
        List<RoleVo> list = RoleMapper.INSTANCE.roleListToVo(page.getContent());
        //封装返回体
        return new PageVo<>((int) page.getTotalElements(), page.getNumber() + 1, page.getSize(), list);
    }

    @Override
    public Page<Role> test() {
        // 将sort解析为Sort对象
        Sort sortObj = JpaUtils.parseSort("id desc");
        // 构造分页对象
        Pageable pageable = PageRequest.of(0, 10, sortObj);
        // 构造查询对象
        //Role role = new Role();
        //role.setCode("ADMIN");
        //Example<Role> example = Example.of(role);

        //return repository.findAllRolesWithLeftJoinFetch(example, pageable);

        //Specification<Role> spec = (root, query, builder) -> {
        //    List<Predicate> predicates = new ArrayList<>();
        //    // 根据需要添加非空字段的查询条件
        //    predicates.add(builder.equal(root.get("code"), "ADMIN"));
        //    predicates.add(builder.equal(root.get("name"), "超级管理员"));
        //    // 添加 LEFT JOIN FETCH 进行多对多关联查询
        //    root.fetch("permissions", JoinType.LEFT);
        //
        //    query.where().getRestriction();
        //    return builder.and(predicates.toArray(new Predicate[0]));
        //};

        //Specification<Role> spec = Specification.where(RoleSpecification.withLeftJoinPermissions())
        //        .and(RoleSpecification.withName("超级管理员"))
        //        .and(RoleSpecification.withCode("ADMIN"));

        Specification<Role> spec = RoleSpecification.combine(
                RoleSpecification.withCode("ADMIN"),
                RoleSpecification.withName(null),
                RoleSpecification.withLeftJoinPermissions()
        );
        return repository.findAll(spec, pageable);
    }
}
