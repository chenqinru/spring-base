package com.eztech.springbase.advice;

import com.eztech.springbase.enums.LogTypeEnums;
import com.eztech.springbase.enums.ResultEnums;
import com.eztech.springbase.exception.CustomException;
import com.eztech.springbase.service.LogService;
import com.eztech.springbase.utils.ExceptionUtils;
import com.eztech.springbase.utils.ResultVoUtils;
import com.eztech.springbase.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;
import java.util.Objects;

/**
 * @author chenqinru
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private LogService logService;

    /**
     * 自定义异常
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CustomException.class)
    public ResultVo<Object> processException(CustomException e) {
        log.error("位置:{} -> 错误信息:{}", ExceptionUtils.getLineInfo(e), e.getLocalizedMessage());
        logService.add(LogTypeEnums.ERROR, e.getLocalizedMessage());
        return ResultVoUtils.fail(e.getLocalizedMessage());
    }

    /**
     * 拦截表单参数校验
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ConstraintViolationException.class, BindException.class})
    public ResultVo<Object> bindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        //Log add = logService.add(LogTypeEnum.INFO, JSON.toJSONString(bindingResult.getTarget()));
        //log.info("请求日志:{}", add);
        logService.add(LogTypeEnums.ERROR, e.getLocalizedMessage());
        log.error("位置:{} -> 错误信息:{}", ExceptionUtils.getLineInfo(e), e.getLocalizedMessage());
        return ResultVoUtils.fail(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /**
     * 拦截JSON参数校验
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVo<Object> bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        //Log add = logService.add(LogTypeEnum.INFO, JSON.toJSONString(bindingResult.getTarget()));
        //log.info("请求日志:{}", add);
        logService.add(LogTypeEnums.ERROR, e.getLocalizedMessage());
        log.error("位置:{} -> 错误信息:{}", ExceptionUtils.getLineInfo(e), e.getLocalizedMessage());
        return ResultVoUtils.fail(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /**
     * 参数格式错误
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultVo<Object> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("位置:{} -> 错误信息:{}", ExceptionUtils.getLineInfo(e), e.getLocalizedMessage());
        logService.add(LogTypeEnums.ERROR, e.getLocalizedMessage());
        return ResultVoUtils.fail(ResultEnums.ARGUMENT_TYPE_MISMATCH);
    }

    /**
     * 请求参数类型不匹配
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultVo<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("位置:{} -> 错误信息:{}", ExceptionUtils.getLineInfo(e), e.getLocalizedMessage());
        logService.add(LogTypeEnums.ERROR, e.getLocalizedMessage());
        return ResultVoUtils.fail(ResultEnums.ARGUMENT_TYPE_MISMATCH);
    }

    /**
     * 参数格式错误
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultVo<Object> httpMessageNotReadable(HttpMessageNotReadableException e) {
        log.error("位置:{} -> 错误信息:{}", ExceptionUtils.getLineInfo(e), e.getLocalizedMessage());
        logService.add(LogTypeEnums.ERROR, e.getLocalizedMessage());
        return ResultVoUtils.fail(ResultEnums.FORMAT_ERROR);
    }

    /**
     * 请求方式不支持
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultVo<Object> httpReqMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.error("位置:{} -> 错误信息:{}", ExceptionUtils.getLineInfo(e), e.getLocalizedMessage());
        logService.add(LogTypeEnums.ERROR, e.getLocalizedMessage());
        return ResultVoUtils.fail(ResultEnums.REQ_METHOD_NOT_SUPPORT);
    }

    /**
     * 实体查找失败
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResultVo<Object> exception(EmptyResultDataAccessException e) {
        log.error("位置:{} -> 错误信息:{}", ExceptionUtils.getLineInfo(e), e.getLocalizedMessage());
        logService.add(LogTypeEnums.ERROR, e.getLocalizedMessage().trim());
        e.printStackTrace();
        return ResultVoUtils.fail(ResultEnums.GET_ERROR);
    }

    /**
     * 403权限不足
     *
     * @param e e
     * @return {@link ResultVo}<{@link ?}>
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AccessDeniedException.class)
    public ResultVo<Object> accessDeniedException(AccessDeniedException e) {
        String msg = ResultEnums.ACCESS_DENIED.getMsg();
        log.error("位置:{} -> 错误信息:{}", ExceptionUtils.getLineInfo(e), msg);
        logService.add(LogTypeEnums.ERROR, msg);
        return ResultVoUtils.fail(ResultEnums.ACCESS_DENIED);
    }

    /**
     * 通用异常
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public ResultVo<Object> exception(Exception e) {
        log.error("位置:{} -> 错误信息:{}", ExceptionUtils.getLineInfo(e), e.getLocalizedMessage());
        logService.add(LogTypeEnums.ERROR, e.getLocalizedMessage().trim());
        e.printStackTrace();
        return ResultVoUtils.fail(e.getLocalizedMessage());
    }
}
