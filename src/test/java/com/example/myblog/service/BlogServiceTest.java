package com.example.myblog.service;


import com.example.myblog.mapper.BlogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BlogServiceTest {

    @Autowired
    BlogService blogService;
    @Autowired
    BlogMapper blogMapper;

    @Test
    void listTest() {
        System.out.println(blogService.list());
    }

    @Test
    void searchTest() {
        System.out.println(blogService.searchBlog(null, null, "test"));
    }

}
