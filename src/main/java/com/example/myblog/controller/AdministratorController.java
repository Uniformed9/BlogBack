package com.example.myblog.controller;

import com.example.myblog.common.R;
import com.example.myblog.service.BlogService;
import com.example.myblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin&{id}")
@Api(tags = "管理员接口")
public class AdministratorController {
    @Autowired
    BlogService blogService;

    @Autowired
    UserService userService;

    @ApiOperation(value = "删除博客")
    @DeleteMapping(path = "/blogs/{blog_id}")
    public R deleteBlog(@PathVariable int blog_id) {
        blogService.deleteBlog(blog_id);
        return R.success();
    }

    @ApiOperation(value = "修改用户状态：0：禁用；1：启用")
    @PostMapping(path = "/usermanage/{user_id}")
    public R modifyUserStatus(@PathVariable int user_id, @RequestBody int status) {
        return R.success(userService.modifyUserStatus(user_id, status));
    }
}
