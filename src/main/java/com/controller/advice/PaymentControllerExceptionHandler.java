package com.controller.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PaymentControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<RepetitionIdExceptionHolder> repetitionIdException() {
        return new ResponseEntity<>(new RepetitionIdExceptionHolder("Possible repetition of a unique key!"), HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @Data
    @AllArgsConstructor
    private static class RepetitionIdExceptionHolder {
        private String message;
    }
}
