package com.boy.springbootallannotationdemos.annotation;

import com.boy.springbootallannotationdemos.entity.Apple;
import com.boy.springbootallannotationdemos.entity.Banana;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-19 11:16
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({Apple.class, Banana.class})
public @interface EnableFruit {

}
