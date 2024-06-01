package com.eliceteam8.edupay.global.advice;

import com.eliceteam8.edupay.global.enums.ExceptionCode;
import com.eliceteam8.edupay.global.exception.AlreadyExistUserException;
import com.eliceteam8.edupay.global.exception.CustomJWTException;
import com.eliceteam8.edupay.global.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("---handleMethodArgumentNotValidException---");
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            stringBuilder.append(fieldError.getField()).append(":");
            stringBuilder.append(fieldError.getDefaultMessage());
            stringBuilder.append(", ");
        }
        final ErrorResponse response = ErrorResponse.of(ExceptionCode.INVALID_INPUT_VALUE, String.valueOf(stringBuilder));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler(AlreadyExistUserException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistUserException(AlreadyExistUserException ex) {
        log.error("---handleAlreadyExistUserException---");
        final ErrorResponse response = ErrorResponse.of(ex.getExceptionCode(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        log.error("---handleUsernameNotFoundException---");
        final ErrorResponse response = ErrorResponse.of(ExceptionCode.NOT_FOUND_USER, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler(CustomJWTException.class)
    public ResponseEntity<ErrorResponse> handleCustomJWTException(CustomJWTException ex) {
        log.error("---handleCustomJWTException---");
        final ErrorResponse response = ErrorResponse.of(ex.getExceptionCode(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }



}
