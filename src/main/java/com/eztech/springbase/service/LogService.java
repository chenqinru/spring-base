package com.eztech.springbase.service;

import com.eztech.springbase.dto.log.ListLogDto;
import com.eztech.springbase.entity.Log;
import com.eztech.springbase.enums.LogTypeEnums;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.log.LogVo;

/**
 * 日志服务
 *
 * @author chenqinru
 * @date 2023/07/19
 */
public interface LogService extends BaseService<Log> {

    /**
     * 列表
     *
     * @param listLogDto 日志列表dto
     * @return {@link PageVo}<{@link LogVo}>
     */
    PageVo<LogVo> list(ListLogDto listLogDto);

    /**
     * 添加
     *
     * @param msg 写入数据
     * @return {@link Log}
     */
    Log add(LogTypeEnums typeEnum, String msg);
}
