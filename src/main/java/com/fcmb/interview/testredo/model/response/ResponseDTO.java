package com.fcmb.interview.testredo.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ResponseDTO<T> {
    private int status;
    private String message;
    private T data;

    public ResponseDTO() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
