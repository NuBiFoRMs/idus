package com.nubiform.idus.config.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.BindException;

@Slf4j
@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(IdusException.class)
    public ErrorResponse idusExceptionHandle(HttpServletRequest request, HttpServletResponse response, IdusException ex) {
        log.error("Request Error : {} {}", ex.getStatus(), ex.getMessage());
        response.setStatus(ex.getStatus());
        return new ErrorResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class, MissingRequestCookieException.class, MethodArgumentTypeMismatchException.class, BindException.class})
    public ErrorResponse badRequestExceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return idusExceptionHandle(request, response, new IdusException(400, ex.getMessage()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorResponse noHandlerFoundExceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return idusExceptionHandle(request, response, new IdusException(404, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse exceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return idusExceptionHandle(request, response, new IdusException(500, ex.getMessage()));
    }

}
