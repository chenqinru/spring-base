package com.eztech.springbase.service;


import com.eztech.springbase.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * 基础服务
 *
 * @author chenqinru
 * @date 2023/07/19
 */
public interface BaseService<T extends BaseEntity> {
    /**
     * 根据id查询
     *
     * @param id id
     * @return {@link T}
     */
    T findById(Integer id);

    /**
     * 根据ids查询
     *
     * @param ids id
     * @return {@link List}<{@link T}>
     */
    List<T> findAllById(List<Integer> ids);

    /**
     * 保存，主键为NULL则新增，否则更新
     *
     * @param entity 实体
     */
    void save(T entity);

    /**
     * 根据id更新所有字段，包括级联的更新或删除
     *
     * @param entity 实体
     */
    void updateAllById(T entity);

    /**
     * 根据id删除
     *
     * @param id id
     */
    void deleteById(Integer id);

    /**
     * 根据id删除所有
     *
     * @param ids id
     */
    void deleteAllById(List<Integer> ids);

    /**
     * 根据分页、排序、搜索条件，查询所有
     *
     * @param page 页面
     * @param size 大小
     * @param sort 排序
     * @param spec 规范
     * @return {@link Page}<{@link T}>
     */
    Page<T> findAll(Integer page, Integer size, String sort, Specification<T> spec);
}
