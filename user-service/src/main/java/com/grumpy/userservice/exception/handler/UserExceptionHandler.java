package com.grumpy.userservice.exception.handler;

import com.grumpy.userservice.exception.EntityNotFoundException;
import com.grumpy.userservice.model.ExceptionResponseModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ExceptionResponseModel handleEntityNotFoundException(EntityNotFoundException ex) {
        return ex.getResponseModel();
    }
}
