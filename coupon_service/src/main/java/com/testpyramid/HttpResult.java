package com.testpyramid;

public class HttpResult<T> {
    private T value;
    private int statusCode;
    private String errorMessage;

    public HttpResult(T value) {
        this.value = value;
    }

    public HttpResult(int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return value != null;
    }

    public T getValue() {
        return value;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
