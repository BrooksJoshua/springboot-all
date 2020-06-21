package com.boy.springbootallsecurity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAllSecurityApplicationTests {

    @Test
    public void contextLoads() {


//        $2a$10$yQ8IG2T11QKrt/fQkYfPoOhd08.BRgra1GB8.cpghLAHDskfesC6y
//        $2a$10$4hElxsRJQYqIlu6MUC0AgOZQaGO9mp2PEaClr6DCOxVMSPmc/vW/G
//        $2a$10$CpMrlhKQuU4tpAIA0kGbMO4t2ltPztJSz9WwGJbDTOn86lOsFFbP2
//        $2a$10$T3miFgK4WvIgrtWqY9hYtepGhVbR2c6TXzGtwLAF.5no8z1kz2Hvi
//        $2a$10$IR9anU4bp89dVM2.PcoyKOtYCrlrLW6.sRgpuz8TeorpiLDtCjYfG
//        $2a$10$R.jFRNG6eTNc.6Uy4t1Lp.f80ji49QVth2.cVl6353kA2C6SVOWjW
//        $2a$10$ELq/57H2kkql2dn63mfmzOLQFvGevAo7aiEQ.12fyZXThcZNC3Mze
//        $2a$10$UhBhqYzxIHGguGZRXtoRx.FagMoqddYCYaGgJSDVE1S43uI3UimOS
//        $2a$10$pb6LZgYNART3eo8bhV0p9eHk5/2mQyTPJlW6tg4Aut9Lqrc9JCSBq
//        $2a$10$bkNTjvp4/XNyOioCzE4/neyQeOSJJX4kfm4UvsILz7sQwb7Ul5jQC
        for (int i = 0; i < 10; i++) {
            System.out.println(new BCryptPasswordEncoder().encode("456"));
        }

    }

}
