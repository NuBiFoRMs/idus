package com.nubiform.idus.config.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class IdusErrorResponse extends IdusResponse {

    public IdusErrorResponse(int status) {
        super(status, "Error");
    }

    public IdusErrorResponse(int status, String message) {
        super(status, message);
    }

    public static IdusErrorResponse of(String message) {
        return new IdusErrorResponse(DEFAULT_ERROR, message);
    }
}
