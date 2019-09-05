package com.devaj.apijwtaws.springapijwtaws.controller;

import com.devaj.apijwtaws.springapijwtaws.exception.ApiError;
import com.devaj.apijwtaws.springapijwtaws.exception.ApiErrorList;
import com.devaj.apijwtaws.springapijwtaws.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException ex){
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), new Date());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> errorList = new ArrayList<String>();
        ex.getBindingResult().getAllErrors().forEach(e -> errorList.add(e.getDefaultMessage()));

        String defauldMessage = "Campos invalidos";

        ApiErrorList apiErrorList = new ApiErrorList(HttpStatus.BAD_REQUEST.value(), defauldMessage, new Date(), errorList);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorList);
    }
}
