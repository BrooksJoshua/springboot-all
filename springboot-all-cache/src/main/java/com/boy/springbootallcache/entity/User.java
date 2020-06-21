package com.boy.springbootallcache.entity;

import java.io.Serializable;

public class User implements Serializable {

    private Integer id;
    private String ame;
    private String author;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", ame='" + ame + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public User(Integer id, String ame, String author) {
        this.id = id;
        this.ame = ame;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAme() {
        return ame;
    }

    public void setAme(String ame) {
        this.ame = ame;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
