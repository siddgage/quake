package com.grumpy.userservice.exception;

import com.grumpy.userservice.model.ExceptionResponseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Getter
@Setter
@Slf4j
public class UserException extends RuntimeException {

    private String errMsg;
    private String timestamp = LocalDateTime.now().toString();

    public UserException(String message){
        super(message);
        this.errMsg = message;
        logError();
    }

    private void logError(){
        log.error(this.errMsg);
    }

    public ExceptionResponseModel getResponseModel(){
        return  new ExceptionResponseModel(errMsg, timestamp);
    }

}
