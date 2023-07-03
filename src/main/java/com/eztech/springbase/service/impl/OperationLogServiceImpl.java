package com.eztech.springbase.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eztech.springbase.entity.OperationLog;
import com.eztech.springbase.mapper.OperationLogMapper;
import com.eztech.springbase.service.IOperationLogService;
import com.eztech.springbase.utils.HttpUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author CQR
 */
@Service
@AllArgsConstructor
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

    @Override
    public OperationLog add(String msg) {
        //请求对象
        Optional<HttpServletRequest> request = HttpUtils.getRequest();
        //日志对象
        OperationLog operationLog = new OperationLog();
        //请求ip
        request.map(HttpUtils::getIpAddress).ifPresent(operationLog::setIp);
        //请求方法
        request.map(HttpServletRequest::getMethod).ifPresent(operationLog::setMethod);
        //请求地址
        request.map(HttpServletRequest::getRequestURI).ifPresent(operationLog::setPath);
        //请求账号
        operationLog.setAccount("admin");
        //请求昵称
        operationLog.setNickname("admin");
        //请求参数
        operationLog.setParams(msg);

        return operationLog;
    }
}
