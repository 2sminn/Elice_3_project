package com.eliceteam8.edupay.global.advice;

import com.eliceteam8.edupay.global.enums.ExceptionCode;
import com.eliceteam8.edupay.global.exception.*;
import com.eliceteam8.edupay.global.response.ErrorResponse;
import jakarta.validation.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("---handleMethodArgumentNotValidException---");

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errors = fieldErrors.stream()
                .map(fieldError ->  fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        final ErrorResponse response = ErrorResponse.builder()
                .status(ExceptionCode.INVALID_INPUT_VALUE.getStatus())
                .code(ExceptionCode.INVALID_INPUT_VALUE.getCode())
                .message(ExceptionCode.INVALID_INPUT_VALUE.getMessage())
                .messageDetail(errors.get(0))
                //.errors(errors)
                .build();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }


    @ExceptionHandler(AlreadyExistUserException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistUserException(AlreadyExistUserException ex) {
        log.error("---handleAlreadyExistUserException---");
        final ErrorResponse response = ErrorResponse.of(ex.getExceptionCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        log.error("---handleUsernameNotFoundException---");
         ErrorResponse response = ErrorResponse.of(ExceptionCode.NOT_FOUND_USER);
         response.setMessageDetail(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("---handleIllegalArgumentException---");
        final ErrorResponse response = ErrorResponse.builder()

                .status(ExceptionCode.INVALID_REQUEST_VALUE.getStatus())
                .code(ExceptionCode.INVALID_REQUEST_VALUE.getCode())
                .message(ExceptionCode.INVALID_REQUEST_VALUE.getMessage())
                // .errors(List.of(ex.getMessage()))
                .messageDetail(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CustomJWTException.class)
    public ResponseEntity<ErrorResponse> handleCustomJWTException(CustomJWTException ex) {
        log.error("---handleCustomJWTException---");
        final ErrorResponse response = ErrorResponse.of(ex.getExceptionCode());
        response.setMessageDetail(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        log.error("---handleSQLIntegrityConstraintViolationException---");

        final ErrorResponse response = new ErrorResponse(ExceptionCode.UNIQUE_VIOLATION);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.error("---handleEntityNotFoundException---");
        final ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .code("ENTITY_NOT_FOUND")
                .message("Entity not found")
                .messageDetail(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MessageTooLongException.class)
    public ResponseEntity<ErrorResponse> handleMessageTooLongException(MessageTooLongException ex) {
        log.error("---handleMessageTooLongException---");
        final ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(ex.getCode())
                .message("Message too long")
                .messageDetail(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotEnoughRemainingBillsException.class)
    public ResponseEntity<ErrorResponse> handleNotEnoughRemainingBillsException(NotEnoughRemainingBillsException ex) {
        log.error("---handleNotEnoughRemainingBillsException---");
        final ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(ex.getCode())
                .message("Not enough remaining bills")
                .messageDetail(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
//        log.error("---handleConstraintViolationException---");
//        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
//        List<String> errors = constraintViolations.stream()
//                .map(constraintViolation ->
//                    extractField(constraintViolation.getPropertyPath()) + ": " + constraintViolation.getMessage())
//                .collect(Collectors.toList());
//
//        ErrorResponse response = ErrorResponse.builder()
//                .status(ExceptionCode.INVALID_INPUT_VALUE.getStatus())
//                .code(ExceptionCode.INVALID_INPUT_VALUE.getCode())
//                .message(ExceptionCode.INVALID_INPUT_VALUE.getMessage())

//              //  .errors(errors)

//                .build();
//
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }


    private String extractField(Path path){
        String[] splitArray = path.toString().split("[.]");
        int lastIndex = splitArray.length - 1;
        return splitArray[lastIndex];
    }



}
