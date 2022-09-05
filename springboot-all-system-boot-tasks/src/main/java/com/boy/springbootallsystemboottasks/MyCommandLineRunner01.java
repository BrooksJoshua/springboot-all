package com.boy.springbootallsystemboottasks;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-04 10:32
 */
@Component
@Order(1) // 数值越小优先级越高， 默认取值是Ordered.LOWEST_PRECEDENCE， 即 最小优先级
public class MyCommandLineRunner01 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception { //该args就是main函数传过来的参数
        System.out.println("########         CommandLineRunner-01       ########");
        System.out.println("args = " + Arrays.toString(args));
    }
}
