package com.stacksimplify.restservices.exceptions;

public class UserNameNotFoundException extends Exception{
    private static final Long serialVersionUID=1L;
    //Super Class Constructor
    public UserNameNotFoundException(String message) {
        super(message);
    }
}
