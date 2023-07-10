package com.eztech.springbase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eztech.springbase.dto.log.ListLogDto;
import com.eztech.springbase.entity.Log;
import com.eztech.springbase.enums.LogTypeEnum;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.log.LogVo;

/**
 * @author CQR
 */
public interface ILogService extends IService<Log> {

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
    Log add(LogTypeEnum typeEnum, String msg);
}
