package com.stacksimplify.restservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {
    @ExceptionHandler(UserNameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorInfo UsernameNotFound(UserNameNotFoundException ex){
        return new CustomErrorInfo(new Date(),ex.getMessage(),"From @RestController Not found");
    }
}
