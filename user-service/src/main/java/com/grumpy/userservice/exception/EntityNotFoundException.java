package com.grumpy.userservice.exception;

public class EntityNotFoundException extends UserException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
