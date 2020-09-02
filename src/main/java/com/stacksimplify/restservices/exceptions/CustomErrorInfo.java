package com.stacksimplify.restservices.exceptions;

//Create Simple Custom Error details Bean

import java.util.Date;

public class CustomErrorInfo {
    private Date timestamp;
    private String message;
    private String errorDetails;
// Field Construction
    public CustomErrorInfo(Date timestamp, String message, String errorDetails) {
        this.timestamp = timestamp;
        this.message = message;
        this.errorDetails = errorDetails;
    }

    //Getters
    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorDetails() {
        return errorDetails;
    }
}
