package com.boy.springbootallweb.entity;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-19 20:53
 */
public class User {
    String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
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
