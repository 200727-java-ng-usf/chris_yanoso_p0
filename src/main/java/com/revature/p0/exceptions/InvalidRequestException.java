package com.revature.p0.exceptions;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException() {
        super();
    }

    public InvalidRequestException(String message){
        super(message);
    }
}
