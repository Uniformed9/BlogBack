package com.example.myblog.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TagServiceTest {

    @Autowired
    TagService tagService;

    @Test
    void listTest(){
        System.out.println(tagService.list());
    }

}
