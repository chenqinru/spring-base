package com.eztech.springbase.utils;


import com.eztech.springbase.enums.ResultEnum;
import com.eztech.springbase.vo.ResultVo;

/**
 * @author CQR
 */
public class ResultVoUtil {

    /**
     * 私有化工具类 防止被实例化
     * j
     */
    private ResultVoUtil() {}

    /**
     * 成功
     * @param data 需要返回的数据
     * @return data
     */
    public static <T> ResultVo<T> ok(T data) {
        ResultVo<T> result = new ResultVo<>();
        result.setCode(ResultEnum.OK.getCode());
        result.setMessage(ResultEnum.OK.getMsg());
        result.setData(data);
        return result;
    }

    /**
     * 成功
     * @return 返回空
     */
    public static  <T> ResultVo<T> ok() {
        return ok(null);
    }

    /**
     * 错误
     * @param resultEnum 错误枚举类
     * @return 错误信息
     */
    public static <T> ResultVo<T> fail(ResultEnum resultEnum) {
        ResultVo<T> result = new ResultVo<>();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMsg());
        return result;
    }

    /**
     * 错误
     * @param code 状态码
     * @param msg 消息
     * @return ResultBean
     */
    public static <T> ResultVo<T> fail(Integer code, String msg) {
        ResultVo<T> result = new ResultVo<>();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    /**
     * 错误
     * @param msg 错误信息
     * @return ResultBean
     */
    public static <T> ResultVo<T> fail(String msg) {
        return fail(ResultEnum.FAIL.getCode(), msg);
    }

    /**
     * 错误
     * @return ResultBean
     */
    public static <T> ResultVo<T> fail() {
        return fail(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMsg());
    }
}
