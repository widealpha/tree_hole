package com.treehole.util;

import com.alibaba.fastjson.JSONObject;

public class Result {
    private Integer code;
    private String message;
    private Object data;

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success(String message) {
        return new Result(0, message, null);
    }

    public static Result success(String message, Object data) {
        return new Result(0, message, data);
    }

    public static Result data(Object data) {
        return success("success", data);
    }

    public static Result error(String message) {
        return new Result(-1, message, null);
    }

    public static Result error(String message, Object data) {
        return new Result(-1, message, data);
    }
    public static Result error(int code, String message) {
        return new Result(code, message, null);
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
