package com.eztech.springbase.service.impl;

import com.eztech.springbase.entity.BaseEntity;
import com.eztech.springbase.enums.ResultEnums;
import com.eztech.springbase.exception.CustomException;
import com.eztech.springbase.repository.BaseRepository;
import com.eztech.springbase.service.BaseService;
import com.eztech.springbase.utils.JpaUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基础服务实现
 *
 * @author chenqinru
 * @date 2023/07/19
 */
@Service
public abstract class BaseServiceImpl<R extends BaseRepository<T>, T extends BaseEntity> implements BaseService<T> {
    //@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    //@Autowired
    protected R repository;

    @Override
    public T findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new CustomException(ResultEnums.GET_ERROR));
    }

    @Override
    public List<T> findAllById(List<Integer> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void save(T entity) {
        repository.save(entity);
    }

    @Override
    public void updateAllById(T entity) {
        repository.save(entity);
    }

    @Override
    public void deleteAllById(List<Integer> ids) {
        repository.deleteAllById(ids);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    //@Override
    //public Page<T> findAll(Integer page, Integer size, String sort, T condition) {
    //    //TODO Specification 可以尝试
    //
    //    // 将sort解析为Sort对象
    //    Sort sortObj = JpaUtils.parseSort(sort);
    //
    //    // 构造分页对象
    //    Pageable pageable = PageRequest.of(page - 1, size, sortObj);
    //
    //    // 构造查询对象
    //    Example<T> example = Example.of(condition);
    //
    //    return repository.findAll(example, pageable);
    //}

    @Override
    public Page<T> findAll(Integer page, Integer size, String sort, Specification<T> spec) {
        // 将sort解析为Sort对象
        Sort sortObj = JpaUtils.parseSort(sort);
        // 构造分页对象
        Pageable pageable = PageRequest.of(page - 1, size, sortObj);
        // Specification查询
        return repository.findAll(spec, pageable);
    }
}
