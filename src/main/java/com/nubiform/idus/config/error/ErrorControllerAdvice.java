package com.nubiform.idus.config.error;

import com.nubiform.idus.config.response.IdusErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
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
    public IdusErrorResponse idusExceptionHandle(HttpServletRequest request, HttpServletResponse response, IdusException ex) {
        log.error("Request Error : {} {}", ex.getStatus(), ex.getMessage());
        response.setStatus(ex.getStatus());
        return new IdusErrorResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class, MissingRequestCookieException.class, MethodArgumentTypeMismatchException.class, BindException.class})
    public IdusErrorResponse badRequestExceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.debug("message : {}", ex.getMessage());
        return idusExceptionHandle(request, response, new IdusException(HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public IdusErrorResponse noHandlerFoundExceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.debug("message : {}", ex.getMessage());
        return idusExceptionHandle(request, response, new IdusException(HttpStatus.NOT_FOUND));
    }

    // 일단추가
    @ExceptionHandler(AuthenticationException.class)
    public IdusErrorResponse authenticationExceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.debug("message : {}", ex.getMessage());
        return idusExceptionHandle(request, response, new IdusException(HttpStatus.UNAUTHORIZED));
    }

    // 일단추가
    @ExceptionHandler(AccessDeniedException.class)
    public IdusErrorResponse accessDeniedExceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.debug("message : {}", ex.getMessage());
        return idusExceptionHandle(request, response, new IdusException(HttpStatus.FORBIDDEN));
    }

    @ExceptionHandler(Exception.class)
    public IdusErrorResponse exceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.debug("message : {}", ex.getMessage());
        return idusExceptionHandle(request, response, new IdusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }

}
