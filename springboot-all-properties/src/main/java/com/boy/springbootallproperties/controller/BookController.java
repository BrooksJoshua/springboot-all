package com.boy.springbootallproperties.controller;

import com.boy.springbootallproperties.entity.AppInfo;
import com.boy.springbootallproperties.entity.Book;
import com.boy.springbootallproperties.entity.NoteBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-20 13:10
 */
@RestController
public class BookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    Book book;
    @Autowired
    NoteBook noteBook;
    @Autowired
    AppInfo appInfo;

    @GetMapping("/book")
    public Object book(){
        return book;
    }

    @GetMapping("/notebook")
    public Object notebook(){
        LOGGER.info(noteBook.toString());
        return appInfo;
    }
}
