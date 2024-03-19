package com.intro.restfulwebservices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@ControllerAdvice
public class UserApiExceptionHandler extends ResponseEntityExceptionHandler implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleUserApiException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                request.getDescription(false),
                java.time.LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                request.getDescription(false),
                java.time.LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDetails, org.springframework.http.HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                "Validation Failed",
                Objects.requireNonNull(ex.getFieldError()).getDefaultMessage(),
                java.time.LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
