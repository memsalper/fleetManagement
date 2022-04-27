package com.works.fleet_management.core.Exceptions;


import com.google.common.collect.Streams;
import com.works.fleet_management.core.utilities.results.ErrorResult;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Stream<String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(x -> x.getField() + " :" + x.getDefaultMessage());
        Stream<String> globalErrors = ex.getBindingResult().getGlobalErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage);
        String error = Streams.concat(fieldErrors, globalErrors).collect(Collectors.joining(" ,"));
        return ResponseEntity.ok(new ErrorResult(error));
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResult> handleDataIntegrityException(DataIntegrityViolationException ex) {
        return ResponseEntity.ok( new ErrorResult(ex.getLocalizedMessage()));
    }



}
