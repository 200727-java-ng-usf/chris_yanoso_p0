package com.revature.p0.exceptions;

public class ResourcePersistenceException extends RuntimeException {

    public ResourcePersistenceException() {
        super();
    }

    public ResourcePersistenceException(String message){
        super(message);
    }
}
