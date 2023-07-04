package com.eztech.springbase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eztech.springbase.entity.Log;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author CQR
 */
@Mapper
public interface OperationLogMapper extends BaseMapper<Log> {

}
