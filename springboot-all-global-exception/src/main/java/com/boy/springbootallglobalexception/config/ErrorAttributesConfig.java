package com.boy.springbootallglobalexception.config;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-03 17:36
 */
@Component
public class ErrorAttributesConfig extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {

        Map<String, Object> map = super.getErrorAttributes(webRequest, options);
        if((Integer)map.get("status") == 404){
            map.put("message","访问路径不存在");
        }
        return map;
    }
}
