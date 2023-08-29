package com.eztech.springbase.service.impl;

import com.eztech.springbase.dto.log.ListLogDto;
import com.eztech.springbase.entity.Log;
import com.eztech.springbase.enums.LogTypeEnums;
import com.eztech.springbase.mapper.LogMapper;
import com.eztech.springbase.repository.LogRepository;
import com.eztech.springbase.service.LogService;
import com.eztech.springbase.specification.LogSpecification;
import com.eztech.springbase.utils.RequestUtils;
import com.eztech.springbase.utils.SecurityUtils;
import com.eztech.springbase.utils.ServletUtils;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.log.LogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * 日志服务实现
 *
 * @author chenqinru
 * @date 2023/07/19
 */
@Service
public class LogServiceImpl extends BaseServiceImpl<LogRepository, Log> implements LogService {
    @Autowired
    public LogServiceImpl(LogRepository repository) {
        this.repository = repository;
    }

    @Override
    public PageVo<LogVo> list(ListLogDto listLogDto) {
        //查询条件
        Specification<Log> spec = LogSpecification.combine(
                LogSpecification.withUsername(listLogDto.getUsername()),
                LogSpecification.withNickname(listLogDto.getNickname()),
                LogSpecification.withMethod(listLogDto.getMethod()),
                LogSpecification.withType(listLogDto.getType())
        );
        //查询
        Page<Log> page = findAll(listLogDto.getPage(), listLogDto.getSize(), listLogDto.getSort(), spec);
        //列表处理
        List<LogVo> list = LogMapper.INSTANCE.logListToVo(page.getContent());
        //List<LogVo> list = page.getContent().stream().map(log -> log.buildVo(new LogVo())).collect(Collectors.toList());
        //封装返回体
        return new PageVo<>((int) page.getTotalElements(), page.getNumber() + 1, page.getSize(), list);
    }

    @Override
    public Log add(LogTypeEnums typeEnum, String msg) {
        //请求对象
        Optional<HttpServletRequest> request = ServletUtils.getRequestAsOptional();
        //日志对象
        Log log = new Log();
        //请求ip
        request.map(RequestUtils::getIpAddress).ifPresent(log::setIp);
        //请求方式
        request.map(HttpServletRequest::getMethod).ifPresent(log::setMethod);
        //请求地址
        request.map(HttpServletRequest::getRequestURI).ifPresent(log::setPath);
        //请求参数
        log.setMsg(msg);
        //日志类型
        log.setType(typeEnum.name());
        //获取登陆用户信息
        SecurityUtils.getLoginUser()
                .ifPresent(user -> {
                    //请求账号
                    log.setUsername(user.getUsername());
                    //请求昵称
                    log.setNickname(user.getNickname());
                });
        //写入数据库
        repository.save(log);

        return log;
    }
}
