package com.boy.springbootallfileupload.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-24 23:29
 */
//@RestControllerAdvice
@ControllerAdvice
public class MyGlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String exceptionCaught(MaxUploadSizeExceededException e){
        return e.getLocalizedMessage();
    }
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptionCaught(Exception e){
        return e.getLocalizedMessage();
    }
    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView exceptionCaught(ArithmeticException e){
        e.printStackTrace();
        ModelAndView mv = new ModelAndView("ArithmeticError");
        mv.addObject("ArithmeticException",e.getMessage());
        return mv;
    }

}
