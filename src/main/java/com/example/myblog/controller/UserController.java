package com.example.myblog.controller;

import com.example.myblog.common.R;
import com.example.myblog.entity.User;
import com.example.myblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RestController
@RequestMapping("/user")
@CrossOrigin
@Api(tags = "用户管理")
public class UserController {
    @Autowired
    UserService userService;
    @ApiOperation("登录")
    @PostMapping("/login")
    public R<User> login(@RequestBody @ApiParam("userName和Password") Map<String, String> map, HttpSession session, ServletRequest servletRequest){

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("Authorization");
        User user = userService.login(map, session);

        if (user!=null){
            return R.success(user);
        }else{
            return R.error("登录失败");
        }
    }
    @ApiOperation("注册")
    @PostMapping("/register")
    public R<User> register( @RequestParam("file") MultipartFile multipartFile, String password,String nickName,String userName,String email,HttpSession session, ServletRequest servletRequest){

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("Authorization");
        User user=userService.Register(userName,password,nickName,email , multipartFile) ;

        if (user!=null){
            return R.success(user);
        }else{
            return R.error("用户名已经存在");
        }
    }

    @GetMapping("/getById")
    public R<User> getById(@RequestParam  Integer id){
        User user=userService.getById(id);
        if(user!=null){
            return R.success(user);
        }else{
            return R.error("没有对应用户");
        }
    }
    @GetMapping("/test")
    public String test(){
        return "测试接口";
    }




}
