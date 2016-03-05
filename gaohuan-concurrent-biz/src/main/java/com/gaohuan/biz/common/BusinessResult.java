package com.gaohuan.biz.common;

/**
 * Created by gh on 2016/3/4 0004.
 */
public class BusinessResult<T> {

    private Boolean success = true;

    private String message;

    private T result;

    public BusinessResult() {
    }

    public BusinessResult(Boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BusinessResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
