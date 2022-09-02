package com.boy.springbootallthymeleaf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
class SpringbootAllThymeleafApplicationTests {

    @Autowired
    TemplateEngine templateEngine;

    @Test
    void contextLoads() {
        Context context = new Context();
        context.setVariable("id",123);
        context.setVariable("name","刘德华");
        context.setVariable("nickname","华仔");
        String process = templateEngine.process("mail/AdmissionNotice", context);
        System.out.println(process);

    }

}
