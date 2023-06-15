package com.example.myblog.controller;

import com.example.myblog.common.R;
import com.example.myblog.service.IBlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin&{id}")
@Api(tags = "管理员接口")
public class AdministratorController {
    @Autowired
    IBlogService blogService;

    @ApiOperation(value = "删除博客")
    @DeleteMapping(path = "/blogs/{blog_id}")
    public R deleteBlog(@PathVariable int blog_id) {
        blogService.deleteBlog(blog_id);
        return R.success();
    }
}
