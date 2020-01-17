package com.token.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一简单封装返回结果集
 *
 * @param <T>
 */
@Data
public class ResultUtil<T> {
    // 返回的状态码
    private Integer code;
    // 提示信息
    private String message;
    // 返回的数据类型
    private T data;

    // 请求成功，无提示信息
    public ResultUtil(T data) {
        this.code = StatusCode.OK;
        this.message = "";
        this.data = data;
    }
    // 请求失败
    public ResultUtil(String message) {
        this.code = StatusCode.ERROR;
        this.message = message;

    }
    // token 失效， 或者 未携带token
    public ResultUtil(String message,T data) {
        this.code = StatusCode.ERROR;
        this.message = message;
        this.data = (T) data;
    }
    // other Constructor  todo...
}
