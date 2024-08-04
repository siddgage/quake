package com.grumpy.userservice.common.exception;

public class EntityNotFoundException extends UserException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
