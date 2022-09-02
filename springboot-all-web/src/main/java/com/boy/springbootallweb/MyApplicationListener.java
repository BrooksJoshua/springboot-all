package com.boy.springbootallweb;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-19 20:31
 */
@Component
public class MyApplicationListener implements ApplicationListener<WebServerInitializedEvent> {
    public void onApplicationEvent(WebServerInitializedEvent event) {
        System.out.println("event.getWebServer().getPort() = " + event.getWebServer().getPort());
    }
}
