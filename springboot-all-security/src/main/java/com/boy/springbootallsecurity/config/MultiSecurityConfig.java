package com.boy.springbootallsecurity.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MultiSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        return bCryptPasswordEncoder;
        //return NoOpPasswordEncoder.getInstance();
    }


    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication() //.passwordEncoder() 也可以在这配置
                .withUser("admin").password("$2a$10$PVDyiHdg7Rzx1XczCcas3uEAeddoqbI4NHjGcP6MvVxAzCmSe./Q.").roles("admin")
                .and()
                .withUser("other").password("$2a$10$4hElxsRJQYqIlu6MUC0AgOZQaGO9mp2PEaClr6DCOxVMSPmc/vW/G").roles("user");
    }



    @Configuration
    @Order(1)
    protected static class AdminConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**").authorizeRequests().anyRequest().hasRole("admin");
        }
    }

    @Configuration
    @Order(2)
    protected static class OtherConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginProcessingUrl("/doLogin").permitAll().and().csrf().disable();
        }
    }



}
