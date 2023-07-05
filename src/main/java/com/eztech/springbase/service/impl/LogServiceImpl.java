package com.eztech.springbase.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eztech.springbase.entity.Log;
import com.eztech.springbase.enums.LogTypeEnum;
import com.eztech.springbase.mapper.OperationLogMapper;
import com.eztech.springbase.service.ILogService;
import com.eztech.springbase.utils.RequestUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author CQR
 */
@Service
@AllArgsConstructor
public class LogServiceImpl extends ServiceImpl<OperationLogMapper, Log> implements ILogService {
    @Override
    public Log add(LogTypeEnum typeEnum, String msg) {
        //请求对象
        Optional<HttpServletRequest> request = RequestUtil.getRequest();
        //日志对象
        Log log = new Log();
        //请求ip
        request.map(RequestUtil::getIpAddress).ifPresent(log::setIp);
        //请求方法
        request.map(HttpServletRequest::getMethod).ifPresent(log::setMethod);
        //请求地址
        request.map(HttpServletRequest::getRequestURI).ifPresent(log::setPath);
        //请求参数
        log.setMsg(msg);
        //日志类型
        log.setType(typeEnum.name());
        //请求账号
        log.setAccount("admin");
        //请求昵称
        log.setNickname("admin");
        //写入数据库
        save(log);

        return log;
    }
}
