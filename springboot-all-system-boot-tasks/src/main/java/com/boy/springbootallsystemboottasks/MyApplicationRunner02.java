package com.boy.springbootallsystemboottasks;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Joshua.H.Brooks
 * @description ApplicationRunner方法的参数比CommandLine参数指定方式丰富一点
 * @date 2022-09-04 10:46
 */
@Component
@Order(4)
public class MyApplicationRunner02 implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("########        ApplicationRunner02         ########");
        //没有键的值
        List<String> nonOptionArgs = args.getNonOptionArgs();
        nonOptionArgs.stream().forEach(System.out::println);
        //获取键值对参数中的所有键
        Set<String> optionNames = args.getOptionNames();
        //再根据键获取值
        for (String name : optionNames) {
            if ("name".equals(name)) {
                List<String> nameValues = args.getOptionValues(name);
                String[] split = nameValues.get(0).split(",");
                System.out.println("first name = " + split[0]);
                System.out.println("last  name = " + split[1]);
            } else {
                System.out.println("(name, value) =" + "--> (" + name + ", " + args.getOptionValues(name) + ")");
            }
        }

        // 上面两种的合集
        String[] sourceArgs = args.getSourceArgs();
        System.out.println("sourceArgs = " + Arrays.toString(sourceArgs));
    }
}
