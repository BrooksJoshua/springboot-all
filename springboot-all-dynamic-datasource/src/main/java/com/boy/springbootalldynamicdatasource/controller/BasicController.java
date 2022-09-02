package com.boy.springbootalldynamicdatasource.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.boy.springbootalldynamicdatasource.bean.ResultBean;

import java.util.List;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-01 16:28
 */
public abstract class BasicController {

    /**
     * 该方法执行如下处操作： <br>
     * 1.封装返回状态和错误信息；<br>
     *
     * @param data
     *            结果集
     * @return 处理后的数据
     */
    protected <T> ResultBean<T> outBound(T data)
            throws IllegalAccessException {

        ResultBean<T> result = new ResultBean<T>();

        result.setData(data);
        result.set__statusCode("1");
        return result;
    }



    /**
     * 封装异常信息，该方法将设置ResultBean的状态代码为E，并返回异常信息
     *
     * @param e
     *            异常
     * @return ResultBean对象
     */
    protected <T> ResultBean<T> errorHandler(Exception e) {

        ResultBean<T> result = new ResultBean<T>();
        result.setData(null);
        result.set__statusCode("0");
        result.set__errorMessage(e.getMessage());

        return result;
    }

    /**
     * 封装异常信息，该方法将设置ResultBean的状态代码为E，并返回异常信息
     * @autor:JiaLing Li
     * @param error 错误 信息
     * @return ResultBean对象
     */
    protected <T> ResultBean<T> errorHandler(String error) {
        ResultBean<T> result = new ResultBean<T>();
        result.setData(null);
        result.set__statusCode("0");
        result.set__errorMessage(error);
        return result;
    }
}

