package com.nubiform.idus.config.error;

import lombok.Getter;

@Getter
public class IdusException extends RuntimeException {

    private int status;

    public IdusException(int status, String message) {
        super(message);
        this.status = status;
    }

    public static IdusException of(String message) {
        return new IdusException(500, message);
    }
}
