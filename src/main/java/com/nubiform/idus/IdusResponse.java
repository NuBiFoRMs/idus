package com.nubiform.idus;

import lombok.Data;

@Data
public class IdusResponse<T> {
    public static final Integer SUCCESS = 200;
    public static final Integer ERROR = 500;

    private int status;
    private T data;
    private String message;

    public IdusResponse(int status, T data) {
        this.status = status;
        this.data = data;
        this.message = "OK";
    }

    public IdusResponse(int status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <D> IdusResponse<D> success(D data) {
        return new IdusResponse<>(SUCCESS, data);
    }

    public static <D> IdusResponse<D> error(String message) {
        return new IdusResponse<>(ERROR, null, message);
    }
}
