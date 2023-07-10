package com.eztech.springbase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eztech.springbase.dto.log.ListLogDto;
import com.eztech.springbase.entity.Log;
import com.eztech.springbase.enums.LogTypeEnum;
import com.eztech.springbase.mapper.LogMapper;
import com.eztech.springbase.service.ILogService;
import com.eztech.springbase.utils.RequestUtil;
import com.eztech.springbase.utils.SecurityUtil;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.log.LogVo;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author CQR
 */
@Service
@AllArgsConstructor
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

    @Override
    public PageVo<LogVo> list(ListLogDto listLogDto) {
        //封装查询对象
        QueryWrapper<Log> queryWrapper = new QueryWrapper<>(listLogDto.buildEntity());
        queryWrapper.orderBy(true, listLogDto.getIsAsc(), listLogDto.getColumn());
        //分页查询
        Page<Log> page = page(new Page<>(listLogDto.getCurrent(), listLogDto.getSize()), queryWrapper);
        List<LogVo> list = page.getRecords().stream().map(log -> log.buildVo(new LogVo())).collect(Collectors.toList());
        //封装返回体
        PageVo<LogVo> pageVo = new PageVo<>();
        BeanUtils.copyProperties(page, pageVo);
        pageVo.setRecords(list);

        return pageVo;
    }

    @Override
    public Log add(LogTypeEnum typeEnum, String msg) {
        //请求对象
        Optional<HttpServletRequest> request = RequestUtil.getRequest();
        //日志对象
        Log log = new Log();
        //请求ip
        request.map(RequestUtil::getIpAddress).ifPresent(log::setIp);
        //请求方式
        request.map(HttpServletRequest::getMethod).ifPresent(log::setMethod);
        //请求地址
        request.map(HttpServletRequest::getRequestURI).ifPresent(log::setPath);
        //请求参数
        log.setMsg(msg);
        //日志类型
        log.setType(typeEnum.name());
        //获取登陆用户信息
        SecurityUtil.getLoginUser()
                .ifPresent(user -> {
                    //请求账号
                    log.setUsername(user.getUsername());
                    //请求昵称
                    log.setNickname(user.getNickname());
                });
        //写入数据库
        save(log);

        return log;
    }
}
