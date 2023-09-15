package com.mav.poc.exception;

import com.mav.poc.model.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@ControllerAdvice
public class CustomExceptionHandler {

    private static final String HYPHEN = " - ";
    private static final String REQUEST_VALIDATION_FIELD = "REQUEST_VALIDATION_FIELD";
    private static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    private static final String ERROR_MESSAGE = "ERROR_WHILE_PROCESSING_REQUEST";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(
                        error -> errors.add(error.getField().concat(HYPHEN).concat(error.getDefaultMessage())));
        ErrorResponse errorResponse = buildErrorResponse(REQUEST_VALIDATION_FIELD, errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllUnCaughtException(
            Exception ex) {
        log.error("CustomExceptionHandler::handleAllUnCaughtException::", ex);
        ErrorResponse errorResponse = buildErrorResponse(INTERNAL_SERVER_ERROR, Arrays.asList(ERROR_MESSAGE));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse buildErrorResponse(String errorCode, List<String> errors) {
        return ErrorResponse.builder().errorCode(errorCode).errors(errors).build();
    }
}
