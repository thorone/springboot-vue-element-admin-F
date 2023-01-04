package com.throne.utils.result;

/**
 * 统一返回结果类
 */
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result() {
    }

    public static<T> Result<T> build(T data){
        Result<T> result = new Result<>();
        if (data != null){
            result.setData(data);
        }
        return result;
    }
    public static<T> Result<T> build(T data,ResultCodeEnum resultCodeEnum){
        Result<T> build = build(data);
        build.setCode(resultCodeEnum.getCode());
        build.setMessage(resultCodeEnum.getMessage());
        return build;
    }
    public static<T> Result<T> ok(T data){
        return build(data,ResultCodeEnum.SUCCESS);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
