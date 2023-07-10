package com.eztech.springbase.controller;

import com.eztech.springbase.dto.log.ListLogDto;
import com.eztech.springbase.enums.ResultEnum;
import com.eztech.springbase.exception.CustomException;
import com.eztech.springbase.service.ILogService;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.log.LogVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author CQR
 */
@RestController
@Api(tags = "日志")
@RequestMapping("/log")
public class LogController {

    @Resource
    private ILogService logService;

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
     */
    @GetMapping("/{id}")
    @ApiOperation("日志详情")
    public LogVo read(@PathVariable Integer id) throws CustomException {
        return Optional.ofNullable(logService.getById(id)).orElseThrow(() -> new CustomException(ResultEnum.GET_ERROR)).buildVo(new LogVo());
    }

    /**
     * 单个或批量删除
     *
     * @param ids id列表
     * @return {@link Boolean}
     */
    @DeleteMapping("/delete")
    @ApiOperation("单个/批量删除用户")
    public Boolean delete(@RequestBody List<Integer> ids) {
        return logService.removeByIds(ids);
    }

}
