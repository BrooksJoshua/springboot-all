package com.boy.springbootallannotationdemos;

import com.boy.springbootallannotationdemos.config.MyConfig;
import com.boy.springbootallannotationdemos.entity.Apple;
import com.boy.springbootallannotationdemos.entity.Author;
import com.boy.springbootallannotationdemos.entity.Banana;
import com.boy.springbootallannotationdemos.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootAllAnnotationDemosApplicationTests {

    @Autowired
    Book book;
    @Autowired
    Author author;
    @Autowired
    MyConfig myConfig;

    @Test
    void testAnnotationConfiguration() {
        System.out.println("(book.getAuthor() == author) = " + (book.getAuthor() == author));
    }

    @Autowired
    Apple apple;
    @Autowired
    Banana banana;

    @Test
    void testInjectionWithAnnotationImport(){
        System.out.println("apple = " + apple);
        System.out.println("banana = " + banana);
    }
}
