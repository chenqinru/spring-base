package com.eztech.springbase.exception;


import com.eztech.springbase.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CQR
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 方法名称
     */
    private final String method;


    /**
     * 自定义异常
     *
     * @param resultEnum 返回枚举对象
     * @param method     方法
     */
    public CustomException(ResultEnum resultEnum, String method) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
        this.method = method;
    }

    /**
     * @param code    状态码
     * @param message 错误信息
     * @param method  方法
     */
    public CustomException(Integer code, String message, String method) {
        super(message);
        this.code = code;
        this.method = method;
    }

}
