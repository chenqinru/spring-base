package com.eztech.springbase.mapper;

import com.eztech.springbase.dto.log.ListLogDto;
import com.eztech.springbase.dto.log.SaveLogDto;
import com.eztech.springbase.entity.Log;
import com.eztech.springbase.vo.log.LogVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 日志映射器
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@Mapper
public interface LogMapper {

    LogMapper INSTANCE = Mappers.getMapper(LogMapper.class);

    LogVo logToVo(Log log);

    List<LogVo> logListToVo(List<Log> logs);

    Log listLogDtoToLog(ListLogDto listLogDto);

    Log saveLogDtoToLog(SaveLogDto saveLogDto);
}
