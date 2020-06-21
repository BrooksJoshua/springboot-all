package com.boy.springbootallsecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

 //@Configuration   //为了测试多个securityconfig而暂时注释掉该配置
 //@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        return bCryptPasswordEncoder;
        //return NoOpPasswordEncoder.getInstance();
    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() //.passwordEncoder() 也可以在这配置
                .withUser("admin").password("$2a$10$VduPah9whd693cG2u7pcYO6nWhZRQLB2QDdw0f9F1r5mQnn1ZwuJC").roles("admin")
                .and()
                .withUser("user").password("$2a$10$A84HwYcgSrxc1ShLziU5UOStNym44nxRTxe0s1nOBPYJO4narIyz.").roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/admin/**").hasRole("admin") //该路径只能admin才能访问
                .antMatchers("/user/**").hasAnyRole("user,admin") //该路径有admin或user任一权限才能访问
        .anyRequest().authenticated()   //其他任何请求登录就能访问
        .and()
        .formLogin()
        .loginProcessingUrl("/doLogin") //处理登录的接口
                .loginPage("/login")
                .usernameParameter("uname")  //默认的登录页面的表单变量用户名和密码是username和password
                .passwordParameter("pwd")   //可以根据具体情况进行指定为其他名称
                .successHandler(new AuthenticationSuccessHandler() {  //前后端分离项目成功后的跳转逻辑, 如果是前后端一体的项目则successForwardUrl(url)表明跳转路径
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        //成功时的参数列表中有一个authentication,其保存的是登录成功后的用户信息
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out =  response.getWriter();
                        Map<Object, Object> map = new HashMap<>();
                        map.put("status","200");
                        map.put("msg",authentication.getPrincipal());
                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();


                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                        //成功时的参数列表中有一个exception,其保存的异常的具体信息
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        Map<Object, Object> map = new HashMap<>();
                        //具体处理不同异常情况
                        if (e instanceof LockedException) {   //AuthenticationException的继承关系可以详细查看有哪些已定义的异常
                            map.put("msg", "账户被锁定");
                        } else if (e instanceof BadCredentialsException) {
                            map.put("msg", "用户名或密码不正确");
                        } else if (e instanceof AccountExpiredException) {
                            map.put("msg", "账户已过期");
                        } else if (e instanceof DisabledException) {
                            map.put("msg","账户已被禁用");
                        }
                    }
                })

        .permitAll()
        .and()
        .csrf().disable() //关闭csrf共计方便测试.
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessHandler(new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest hsrq, HttpServletResponse hsrp, Authentication authentication) throws IOException, ServletException {
                        hsrp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = hsrp.getWriter();
                        Map map=new HashMap<>();
                        map.put("status","200");
                        map.put("msg","You've logged out, successfully");
                        authentication.setAuthenticated(false);
                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();


            }
        });
    }
}
