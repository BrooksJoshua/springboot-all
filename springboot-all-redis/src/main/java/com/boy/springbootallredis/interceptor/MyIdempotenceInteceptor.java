package com.boy.springbootallredis.interceptor;

import com.boy.springbootallredis.anno.MyIdempotenceEnabled;
import com.boy.springbootallredis.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Joshua.H.Brooks
 * @description 幂等性问题的拦截器
 * @date 2022-09-05 15:55
 */
@Component
public class MyIdempotenceInteceptor implements HandlerInterceptor {
    @Autowired
    TokenService tokenService;

    /**
     * @param request
     * @param response
     * @param handler
     * @return true放行， false拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        MyIdempotenceEnabled annotation = method.getAnnotation(MyIdempotenceEnabled.class);
        if (annotation != null) {
            try {
                return tokenService.checkToken(request); //开启幂等性保护的接口请求，校验token通过就放行。
            } catch (Exception e) {
                throw e;
            }
        }
        return true; //未开启幂等性保护的接口请求， 不会进入上面的校验， 直接放行。
    }
}
