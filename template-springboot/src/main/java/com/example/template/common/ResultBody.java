package com.example.template.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: mai
 * @since: 2023/4/11
 */
@Data
public class ResultBody<T> implements Serializable {

    /**
     * 响应编码
     */
    private String code;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;

    public ResultBody(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultBody<T> success() {
        return new ResultBody<>("200", "success" ,null);
    }

    public static <T> ResultBody<T> success(T data) {
        return new ResultBody<>("200", "success", data);
    }

    public static <T> ResultBody<T> error(String code) {
        return new ResultBody<>(code, "error", null);
    }

    public static <T> ResultBody<T> error(String code, T data) {
        return new ResultBody<>(code, "error", data);
    }

}
