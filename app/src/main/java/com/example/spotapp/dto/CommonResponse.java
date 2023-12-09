package com.example.spotapp.dto;

import java.util.List;

public class CommonResponse<T> {

    private String status;
    private T data;
    private String message;

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
