package com.nubiform.idus.config.response;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class IdusErrorResponse extends IdusResponse {

    public IdusErrorResponse() {
        super(DEFAULT_ERROR, "Error");
    }
    public IdusErrorResponse(int status) {
        super(status, "Error");
    }

    public IdusErrorResponse(int status, String message) {
        super(status, message);
    }

    public IdusErrorResponse(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public static IdusErrorResponse of(String message) {
        return new IdusErrorResponse(DEFAULT_ERROR, message);
    }
}
