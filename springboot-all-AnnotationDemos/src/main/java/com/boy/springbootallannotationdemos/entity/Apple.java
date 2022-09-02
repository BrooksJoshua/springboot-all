package com.boy.springbootallannotationdemos.entity;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-19 11:15
 */
public class Apple {
    String name;

    @Override
    public String toString() {
        return "Apple{" +
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
