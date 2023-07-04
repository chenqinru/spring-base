package com.eztech.springbase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eztech.springbase.entity.Log;
import com.eztech.springbase.enums.LogTypeEnum;

/**
 * @author CQR
 */
public interface ILogService extends IService<Log> {
    /**
     * 添加
     *
     * @param msg 写入数据
     * @return {@link Log}
     */
    Log add(LogTypeEnum typeEnum, String msg);
}
