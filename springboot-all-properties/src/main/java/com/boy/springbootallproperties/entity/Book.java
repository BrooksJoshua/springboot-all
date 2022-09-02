package com.boy.springbootallproperties.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-20 13:11
 */
@Component
public class Book {
    @Value("${book.name}")
    private String name;
    @Value("${book.author}")
    private String author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
