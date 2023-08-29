package com.eztech.springbase.exception;


import com.eztech.springbase.enums.ResultEnums;
import lombok.EqualsAndHashCode;

/**
 * @author chenqinru
 */
@EqualsAndHashCode(callSuper = false)
public class RateLimiterException extends CustomException {
    /**
     * 自定义异常
     *
     * @param resultEnums 返回枚举对象
     */
    public RateLimiterException(ResultEnums resultEnums) {
        super(resultEnums);
    }

    /**
     * @param code    状态码
     * @param message 错误信息
     */
    public RateLimiterException(Integer code, String message) {
        super(code, message);
    }
}
