package com.eztech.springbase.exception;


import com.eztech.springbase.enums.ResultEnums;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenqinru
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 自定义异常
     *
     * @param resultEnums 返回枚举对象
     */
    public CustomException(ResultEnums resultEnums) {
        super(resultEnums.getMsg());
        this.code = resultEnums.getCode();
    }

    /**
     * @param code    状态码
     * @param message 错误信息
     */
    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
