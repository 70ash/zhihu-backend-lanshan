package com.forzlp.zhihubackend.utils;

import lombok.Data;

/**
 * @author zlp
 * @date 2023/8/4 19:45
 * 自己的返回类,封装返回的信息
 */
@Data

public class Result<T> {
    // 状态码
    private Integer code;
    // 信息
    private String msg;
    // 数据
    private T data;

    public Result() {
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 成功
    public static <T> Result<T> success(){
        Result<T> result = build(200);
        return result;
    }
    // 成功
    public static <T> Result<T> success(T data){
        Result<T> result = build(200,data);
        return result;
    }
    // 失败
    public static <T> Result<T> fail() {
        Result<T> result = build(400);
        return result;
    }
    // 失败
    public static <T> Result<T> fail(T data) {
        Result<T> result = build(400,data);
        return result;
    }
    // 有数据的构建
    private static <T> Result<T> build(Integer code,T data) {
        Result<T> result;
        if(code == 200) {
            result = new Result<>(code,"成功",data);
        }else {
            result = new Result<>(code,"失败",data);
        }
        return result;
    }
    // 没有数据的构建
    private static <T> Result<T> build(Integer code) {
        Result<T> result;
        if (code == 200) {
            result = new Result<>(200,"成功",null);
        }else {
            result = new Result<>(200,"失败",null);
        }
        return result;
    }
}
