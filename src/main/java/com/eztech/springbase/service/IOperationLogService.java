package com.eztech.springbase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eztech.springbase.entity.OperationLog;

/**
 * @author CQR
 */
public interface IOperationLogService extends IService<OperationLog> {
    /**
     * 添加
     *
     * @param msg 写入数据
     * @return {@link OperationLog}
     */
    OperationLog add(String msg);
}
