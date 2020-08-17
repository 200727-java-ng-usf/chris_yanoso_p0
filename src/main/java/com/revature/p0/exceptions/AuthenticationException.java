package com.revature.p0.exceptions;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(){
        super();
    }

    public AuthenticationException(String message){
        super(message);

    }
}
