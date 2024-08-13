package org.example.order_service.exception;

import jakarta.servlet.ServletException;
import jakarta.validation.ValidationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler({TypeMismatchException.class, EntityNotFoundException.class, MultipartException.class})
    public ResponseEntity<ErrorResponse> entityException(Exception e) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({BindException.class, ValidationException.class, DataIntegrityViolationException.class})
    public ResponseEntity<ErrorResponse> validateException(Exception e) {
        ErrorResponse response = new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorResponse> httpRequestMethodNotSupportedException(ServletException e) {
        ErrorResponse response = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({NoHandlerFoundException.class, })
    public ResponseEntity<ErrorResponse> noHandlerFoundException(Exception e) {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
