package com.nubiform.idus.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubiform.idus.config.response.IdusErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
public class IdusAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.debug("IdusAccessDeniedHandler : 403");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        try (OutputStream outputStream = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(outputStream, new IdusErrorResponse(HttpStatus.FORBIDDEN));
            outputStream.flush();
        }
    }
}
