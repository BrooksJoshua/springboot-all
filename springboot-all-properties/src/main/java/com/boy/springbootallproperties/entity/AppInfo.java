package com.boy.springbootallproperties.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-20 22:57
 */
@Component
public class AppInfo {
    @Value("${journal.name}")
    private String name;

    @Override
    public String toString() {
        return "AppInfo{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
