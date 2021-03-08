package com.nubiform.idus.config.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class IdusDataResponse<T> extends IdusResponse {

    private final T data;

    public IdusDataResponse(int status, T data) {
        super(status);
        this.data = data;
    }

    public IdusDataResponse(int status, String message, T data) {
        super(status, message);
        this.data = data;
    }

    public static <D> IdusDataResponse<D> success(D data) {
        return new IdusDataResponse<>(DEFAULT_SUCCESS, data);
    }
}
