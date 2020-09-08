package com.stacksimplify.restservices.exceptions;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    //private static final Long serialVersionUID =1L;
    //MethodArgumentNotValidException
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorInfo customErrorInfo = new CustomErrorInfo(new Date(), "From Method Argument Not Valid Exception GEH", ex.getLocalizedMessage());
        return new ResponseEntity<>(customErrorInfo, HttpStatus.BAD_REQUEST);
    }

    //handleHttpRequestMethodNotSupported
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorInfo customErrorInfo = new CustomErrorInfo(new Date(), "HttpRequestMethod Not Valid Exception GEH", ex.getMethod());
        return new ResponseEntity<>(customErrorInfo, HttpStatus.METHOD_NOT_ALLOWED);
    }

    //UserNameNotFoundExceptionHandler
    @ExceptionHandler(UserNameNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNameNotFoundException ex, WebRequest request) {
        CustomErrorInfo customErrorInfo = new CustomErrorInfo(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(customErrorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
        CustomErrorInfo customErrorInfo = new CustomErrorInfo(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(customErrorInfo,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
        CustomErrorInfo customErrorInfo= new CustomErrorInfo(new Date(), ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(customErrorInfo,HttpStatus.NOT_FOUND);
    }



}
