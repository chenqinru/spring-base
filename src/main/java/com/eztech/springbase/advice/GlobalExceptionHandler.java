package com.eztech.springbase.advice;

import com.eztech.springbase.enums.LogTypeEnum;
import com.eztech.springbase.enums.ResultEnum;
import com.eztech.springbase.exception.CustomException;
import com.eztech.springbase.service.ILogService;
import com.eztech.springbase.utils.ExceptionUtil;
import com.eztech.springbase.utils.ResultVoUtil;
import com.eztech.springbase.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author CQR
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private ILogService logService;

    /**
     * 自定义异常
     */
    @ExceptionHandler(value = CustomException.class)
    public ResultVo<?> processException(CustomException e) {
        log.error("位置:{} -> 错误信息:{}", ExceptionUtil.getLineInfo(e), e.getLocalizedMessage());
        logService.add(LogTypeEnum.ERROR, e.getLocalizedMessage());
        return ResultVoUtil.fail(Objects.requireNonNull(ResultEnum.getByCode(e.getCode())));
    }

    /**
     * 拦截表单参数校验
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BindException.class})
    public ResultVo<?> bindException(BindException e) {
        log.error("位置:{} -> 错误信息:{}", ExceptionUtil.getLineInfo(e), e.getLocalizedMessage());
        logService.add(LogTypeEnum.ERROR, e.getLocalizedMessage());
        BindingResult bindingResult = e.getBindingResult();
        return ResultVoUtil.fail(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /**
     * 拦截JSON参数校验
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVo<?> bindException(MethodArgumentNotValidException e) {
        log.error("位置:{} -> 错误信息:{}", ExceptionUtil.getLineInfo(e), e.getLocalizedMessage());
        logService.add(LogTypeEnum.ERROR, e.getLocalizedMessage());
        BindingResult bindingResult = e.getBindingResult();
        return ResultVoUtil.fail(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /**
     * 参数格式错误
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultVo<?> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("位置:{} -> 错误信息:{}", ExceptionUtil.getLineInfo(e), e.getLocalizedMessage());
        logService.add(LogTypeEnum.ERROR, e.getLocalizedMessage());
        return ResultVoUtil.fail(ResultEnum.ARGUMENT_TYPE_MISMATCH);
    }

    /**
     * 参数格式错误
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultVo<?> httpMessageNotReadable(HttpMessageNotReadableException e) {
        log.error("位置:{} -> 错误信息:{}", ExceptionUtil.getLineInfo(e), e.getLocalizedMessage());
        logService.add(LogTypeEnum.ERROR, e.getLocalizedMessage());
        return ResultVoUtil.fail(ResultEnum.FORMAT_ERROR);
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultVo<?> httpReqMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.error("位置:{} -> 错误信息:{}", ExceptionUtil.getLineInfo(e), e.getLocalizedMessage());
        logService.add(LogTypeEnum.ERROR, e.getLocalizedMessage());
        return ResultVoUtil.fail(ResultEnum.REQ_METHOD_NOT_SUPPORT);
    }

    /**
     * 通用异常
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public ResultVo<?> exception(Exception e) {
        log.error("位置:{} -> 错误信息:{}", ExceptionUtil.getLineInfo(e), e.getLocalizedMessage());
        logService.add(LogTypeEnum.ERROR, e.getLocalizedMessage().trim());
        e.printStackTrace();
        return ResultVoUtil.fail(ResultEnum.UNKNOWN_EXCEPTION);
    }
}
