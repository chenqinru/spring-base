package com.eztech.springbase.controller;

import com.eztech.springbase.dto.log.ListLogDto;
import com.eztech.springbase.exception.CustomException;
import com.eztech.springbase.mapper.LogMapper;
import com.eztech.springbase.service.LogService;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.log.LogVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 日志控制器
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@RestController
@Api(tags = "日志")
@RequestMapping("/log")
public class LogController {

    @Resource
    private LogService logService;

    /**
     * 日志列表
     *
     * @param listLogDto 日志列表dto
     * @return {@link PageVo}<{@link LogVo}>
     */
    @GetMapping("/list")
    @ApiOperation("日志列表")
    public PageVo<LogVo> list(@Validated ListLogDto listLogDto) {
        return logService.list(listLogDto);
    }

    /**
     * 日志详情
     *
     * @param id id
     * @return {@link LogVo}
     * @throws CustomException 自定义异常
     */
    @GetMapping("/{id}")
    @ApiOperation("日志详情")
    public LogVo read(@PathVariable Integer id) throws CustomException {
        return LogMapper.INSTANCE.logToVo(logService.findById(id));
    }

    /**
     * 单个或批量删除
     *
     * @param ids id列表
     */
    @DeleteMapping("/delete")
    @ApiOperation("单个或批量删除用户")
    public void delete(@RequestBody List<Integer> ids) {
        logService.deleteAllById(ids);
    }

}
