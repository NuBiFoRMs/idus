package com.nubiform.idus.config.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class IdusException extends RuntimeException {

    private int status;

    public IdusException(int status, String message) {
        super(message);
        this.status = status;
    }

    public IdusException(HttpStatus httpStatus) {
        super(httpStatus.getReasonPhrase());
        this.status = httpStatus.value();
    }

    public static IdusException of(String message) {
        return new IdusException(500, message);
    }
}
