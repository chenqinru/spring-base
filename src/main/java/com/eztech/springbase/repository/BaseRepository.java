package com.eztech.springbase.repository;

import com.eztech.springbase.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 基础库
 *
 * @author chenqinru
 * @date 2023/07/19
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Integer> , JpaSpecificationExecutor<T> {

}
