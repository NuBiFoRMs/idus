package com.nubiform.idus.config.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class IdusResponse {
    public static final int DEFAULT_SUCCESS = 200;
    public static final int DEFAULT_ERROR = 500;

    private final int status;
    private final String message;

    public IdusResponse(int status) {
        this.status = status;
        this.message = "OK";
    }

    public IdusResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static IdusResponse of(String message) {
        return new IdusResponse(DEFAULT_SUCCESS, message);
    }
}
