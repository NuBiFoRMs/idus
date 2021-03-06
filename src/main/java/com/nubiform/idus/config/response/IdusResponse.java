package com.nubiform.idus.config.response;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class IdusResponse {
    public static final int DEFAULT_SUCCESS = 200;
    public static final int DEFAULT_ERROR = 500;

    private final int status;
    private final String message;

    public IdusResponse() {
        this.status = DEFAULT_SUCCESS;
        this.message = "OK";
    }

    public IdusResponse(int status) {
        this.status = status;
        this.message = "OK";
    }

    public IdusResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public IdusResponse(HttpStatus httpStatus) {
        this.status = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }

    public static IdusResponse of(String message) {
        return new IdusResponse(DEFAULT_SUCCESS, message);
    }
}
