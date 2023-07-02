package com.eztech.springbase.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eztech.springbase.entity.OperationLog;
import com.eztech.springbase.mapper.OperationLogMapper;
import com.eztech.springbase.service.IOperationLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author CQR
 */
@Service
@AllArgsConstructor
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

}
