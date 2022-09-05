package com.boy.springbootallredis.exception;

/**
 * @author Joshua.H.Brooks
 * @description 幂等性相关的异常
 * @date 2022-09-05 15:45
 */
public class IdempotenceException extends Exception {
    public IdempotenceException(String msg) {
        super(msg);
    }
}
