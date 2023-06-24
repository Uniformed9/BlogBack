package com.example.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myblog.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


public interface UserService extends IService<User> {

    User login(@RequestBody Map<String,String> map, HttpSession session);
    Map<Integer,User> getUserMap();
    User Register(String userName,String password,String nickName,String email , MultipartFile avatar) ;

        User selectUserByuserId(int id);

    boolean modifyUserStatus(int id, int status);
}
