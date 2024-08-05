package com.shortenurl.shortenurl.exception;

import com.shortenurl.shortenurl.infos.ErrorInfo;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalException extends RuntimeException{

    @ExceptionHandler(ShortURLNotFoundException.class)
    public ResponseEntity<ErrorInfo> shortURLNotExceptionHandler(ShortURLNotFoundException exception, WebRequest request) {
        ErrorInfo error = new ErrorInfo(HttpStatus.NOT_FOUND, LocalDate.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateShortURLException.class)
    public ResponseEntity<ErrorInfo> duplicateShortURLExceptionHandler(DuplicateShortURLException exception, WebRequest request) {
        ErrorInfo error = new ErrorInfo(HttpStatus.BAD_REQUEST, LocalDate.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception, WebRequest request) {
        ErrorInfo error = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR, LocalDate.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
