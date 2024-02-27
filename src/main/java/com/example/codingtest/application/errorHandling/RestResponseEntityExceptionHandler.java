package com.example.codingtest.application.errorHandling;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Set;

@ControllerAdvice
public class RestResponseEntityExceptionHandler 
  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value  = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleValidationError(ConstraintViolationException ex, WebRequest request) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<String> list = constraintViolations.stream().map(ConstraintViolation::getMessageTemplate).toList();
        ErrorResponse build = ErrorResponse.builder().errors(list).build();
        return ResponseEntity.badRequest().body(build);
    }
}