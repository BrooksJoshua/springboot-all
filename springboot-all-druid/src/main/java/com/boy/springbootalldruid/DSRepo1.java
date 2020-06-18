package com.boy.springbootalldruid;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * created by Joshua.H.Brooks on 2020.6月.13.17.13
 */

@Component
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DSRepo1 {
    String value() default "";
}
