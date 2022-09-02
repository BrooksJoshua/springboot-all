package com.boy.springbootallannotationdemos.entity;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-19 10:10
 */
public class Book {
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public Book(Author author) {
        this.author = author;
    }
}
