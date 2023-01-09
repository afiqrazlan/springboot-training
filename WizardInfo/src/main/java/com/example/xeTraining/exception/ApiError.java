package com.example.xeTraining.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiError {

    @Autowired
    private Tracer tracer;

    private static final Logger logger = LoggerFactory.getLogger(ApiError.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex)
    {
        Span span = tracer.currentSpan();
        String traceID = "";
        if (span != null) {
            logger.info("Trace ID for Invalid Input: {}", span.context().traceId(),ex);
            traceID = span.context().traceId();
        }

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,"NOK",1, traceID);
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        errorResponse.setErrors(errors);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WizardNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(WizardNotFoundException ex)
    {
        Span span = tracer.currentSpan();
        String traceID ="";
        if (span != null) {
            logger.info("Trace ID for UserNotFoundException: {}", span.context().traceId(),ex);
            traceID = span.context().traceId();
        }

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND,"NOK",1,traceID);
        List<String> errors = Collections.singletonList(ex.getMessage());
        errorResponse.setErrors(errors);

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralExceptionErrors(Exception ex)
    {
        Span span = tracer.currentSpan();
        String traceID = "";
        if (span != null) {
            logger.info("Trace ID for Exception errors: {}", span.context().traceId(),ex);
            traceID = span.context().traceId();
        }
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"NOK",1,traceID);
        List<String> errors = Collections.singletonList(ExceptionUtils.getRootCauseMessage(ex));
        errorResponse.setErrors(errors);

        errorResponse.setErrors(errors);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeExceptionErrors(RuntimeException ex)
    {
        Span span = tracer.currentSpan();
        String traceID = "";
        if (span != null) {
            logger.info("Trace ID for Runtime Exception errors: {}", span.context().traceId(),ex);
            traceID = span.context().traceId();
        }

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"NOK",1,traceID);
        List<String> errors = Collections.singletonList(ExceptionUtils.getRootCauseMessage(ex));
        errorResponse.setErrors(errors);

        errorResponse.setErrors(errors);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
