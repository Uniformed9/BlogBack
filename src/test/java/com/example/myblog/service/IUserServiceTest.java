package com.example.myblog.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class IUserServiceTest {
    @Autowired
    IUserService userService;


    @Test
    void loginTest() {
        System.out.println(userService.list());
    }
}