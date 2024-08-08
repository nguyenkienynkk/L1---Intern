package com.globits.da.exception;

public class ErrorResponse {
    private String errorCode;
    private String getErrorMessage;
    public ErrorResponse (String errorCode , String getErrorMessage){
        this.errorCode = errorCode;
        this.getErrorMessage = getErrorMessage;
    }
}
