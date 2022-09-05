//package com.boy.springbootallglobalexception.config;
//
//import org.springframework.boot.autoconfigure.web.ResourceProperties;
//import org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver;
//import org.springframework.context.ApplicationContext;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author Joshua.H.Brooks
// * @description
// * @date 2022-09-03 17:37
// */
////@Component
//public class ErrorViewResolverConfig extends DefaultErrorViewResolver {
//    public ErrorViewResolverConfig(ApplicationContext applicationContext, ResourceProperties resourceProperties) {
//        super(applicationContext, resourceProperties);
//    }
//
//    @Override
//    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
//        //model是UnmodifiableMap 直接修改会报错
//        HashMap<Object, Object> map = new HashMap<>();
//        map.putAll(model);
//        if((Integer) model.get("status")==500){
//            model.put("message","服务器内部错误");
//        }
//        //ModelAndView view = new ModelAndView("5XX",map);
//
//        return view;
//    }
//}
