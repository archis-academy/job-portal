package com.archisacademy.jobportal.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@SuppressWarnings("serial")
public class JobPortalServerException extends RuntimeException{
    private static final long serialVersionUID = 62348743L;

    private HttpStatus httpStatusCode;

    public JobPortalServerException(){
        super("Unexpected Exception encountered.");
    }

    public JobPortalServerException(String message,HttpStatus httpStatus){
        super(message);
        this.httpStatusCode=httpStatus;
    }

}
