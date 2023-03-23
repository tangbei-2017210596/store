package com.tb.store.util;

import lombok.Data;

import java.io.Serializable;

/**
 * Json格式的数据结果 （返回给网页）
 */
@Data
public class JsonResult<T> implements Serializable {
    //状态码
    private Integer state;
    //描述信息
    private String message;
    private T data;

    public JsonResult() {
    }

    public JsonResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state) {
        this.state = state;
    }
    public JsonResult(Throwable e) {
        this.message=e.getMessage();
    }
}
