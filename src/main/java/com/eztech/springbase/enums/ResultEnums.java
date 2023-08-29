package com.eztech.springbase.enums;


import lombok.Getter;

/**
 * @author chenqinru
 */
@Getter
public enum ResultEnums {

    /**
     * 失败
     */
    FAIL(-1, "fail"),

    /**
     * 成功
     */
    OK(0, "ok"),

    /**
     * 未知异常
     */
    UNKNOWN_EXCEPTION(100, "未知异常"),

    /**
     * 格式错误
     */
    FORMAT_ERROR(101, "参数格式错误"),

    /**
     * 超时
     */
    TIME_OUT(102, "超时"),

    /**
     * 添加失败
     */
    ADD_ERROR(103, "添加失败"),

    /**
     * 更新失败
     */
    UPDATE_ERROR(104, "更新失败"),

    /**
     * 删除失败
     */
    DELETE_ERROR(105, "删除失败"),

    /**
     * 查找失败
     */
    GET_ERROR(106, "查找失败"),

    /**
     * 参数类型不匹配
     */
    ARGUMENT_TYPE_MISMATCH(107, "参数类型不匹配"),

    /**
     * 请求方式不支持
     */
    REQ_METHOD_NOT_SUPPORT(110, "请求方式不支持"),

    /**
     * token为空
     */
    TOKEN_EMPTY(201, "token为空"),

    /**
     * token无效
     */
    TOKEN_INVALID(202, "token无效"),

    /**
     * token过期
     */
    TOKEN_EXPIRED(203, "token过期"),

    /**
     * 访问被拒绝
     */
    ACCESS_DENIED(301, "访问被拒绝"),
    ;

    /**
     * 代码
     */
    private final Integer code;

    /**
     * 信息
     */
    private final String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 通过状态码获取枚举对象
     *
     * @param code 状态码
     * @return 枚举对象
     */
    public static ResultEnums getByCode(int code) {
        for (ResultEnums resultEnums : ResultEnums.values()) {
            if (code == resultEnums.getCode()) {
                return resultEnums;
            }
        }
        return null;
    }

}

