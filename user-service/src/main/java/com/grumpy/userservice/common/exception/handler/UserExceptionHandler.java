package com.grumpy.userservice.common.exception.handler;

import com.grumpy.userservice.common.exception.EntityNotFoundException;
import com.grumpy.userservice.common.model.ExceptionResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class UserExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ExceptionResponseModel handleEntityNotFoundException(EntityNotFoundException ex) {
        return ex.getResponseModel();
    }
}
