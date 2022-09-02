package com.boy.springbootalldynamicdatasource.bean;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-01 16:31
 */
public final class ResultBean<T> {

    private String __statusCode;// 0:失败,1:成功
    private String __errorMessage;
    private T data;

    public String get__statusCode() {
        return __statusCode;
    }
    public void set__statusCode(String __statusCode) {
        this.__statusCode = __statusCode;
    }
    public String get__errorMessage() {
        return __errorMessage;
    }
    public void set__errorMessage(String __errorMessage) {
        this.__errorMessage = __errorMessage;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }


}