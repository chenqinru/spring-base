package com.eztech.springbase.utils;


import com.eztech.springbase.enums.ResultEnums;
import com.eztech.springbase.vo.ResultVo;


/**
 * 相应工具类
 *
 * @author chenqinru
 * @date 2023/07/04
 */
public final class ResultVoUtils {

    /**
     * 私有化工具类 防止被实例化
     * j
     */
    private ResultVoUtils() {
    }

    /**
     * 成功
     *
     * @param data 需要返回的数据
     * @return data
     */
    public static <T> ResultVo<T> ok(T data) {
        ResultVo<T> result = new ResultVo<>();
        result.setCode(ResultEnums.OK.getCode());
        result.setMessage(ResultEnums.OK.getMsg());
        result.setData(data);
        return result;
    }

    /**
     * 成功
     *
     * @return 返回空
     */
    public static <T> ResultVo<T> ok() {
        return ok(null);
    }

    /**
     * 错误
     *
     * @param resultEnums 错误枚举类
     * @return 错误信息
     */
    public static <T> ResultVo<T> fail(ResultEnums resultEnums) {
        ResultVo<T> result = new ResultVo<>();
        result.setCode(resultEnums.getCode());
        result.setMessage(resultEnums.getMsg());
        return result;
    }

    /**
     * 错误
     *
     * @param code 状态码
     * @param msg  消息
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
     *
     * @param msg 错误信息
     * @return ResultBean
     */
    public static <T> ResultVo<T> fail(String msg) {
        return fail(ResultEnums.FAIL.getCode(), msg);
    }

    /**
     * 错误
     *
     * @return ResultBean
     */
    public static <T> ResultVo<T> fail() {
        return fail(ResultEnums.FAIL.getCode(), ResultEnums.FAIL.getMsg());
    }
}
