package com.example.springboot.exceptions;

import com.example.springboot.dtos.DefaultError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        log.info("Error treated by Exception Handler.");
        String messageError = "Erro ao processar sua requisição";
        DefaultError defaultError = new DefaultError(HttpStatus.BAD_GATEWAY.value(), messageError);
        return new ResponseEntity(defaultError, HttpStatus.BAD_GATEWAY);
    }


}
