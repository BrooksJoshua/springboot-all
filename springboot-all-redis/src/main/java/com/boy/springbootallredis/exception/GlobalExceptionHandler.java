package com.boy.springbootallredis.exception;

import com.boy.springbootallredis.exception.IdempotenceException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Joshua.H.Brooks
 * @description 全局异常处理
 * @date 2022-09-05 16:10
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IdempotenceException.class)
    public String handleIdempotenceException(IdempotenceException e){
        return e.getLocalizedMessage();
    }
}
