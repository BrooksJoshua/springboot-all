package com.boy.springbootallredis.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Joshua.H.Brooks
 * @description 建立注解， 被此注解修饰的方法即开启了幂等性保护
 * @date 2022-09-05 15:29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyIdempotenceEnabled {
}
