package com.nubiform.idus.config.error;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorResponse {
    public static final Integer DEFAULT_ERROR = 500;

    private final int status;
    private final String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorResponse of(String message) {
        return new ErrorResponse(DEFAULT_ERROR, message);
    }
}
