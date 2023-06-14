package com.example.myblog.controller;

import com.example.myblog.common.R;
import com.example.myblog.service.IBlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user/{id}/home/blogs")
@Api(tags = "个人博客接口")
public class PersonalBlogController {
    @Autowired
    IBlogService blogService;

    @ApiOperation(value = "我的博客")
    @GetMapping()
    public R myBlogs(@PathVariable int id) {
        System.out.println("in!");
        return R.success(blogService.personalBlogs(id));
    }

    @ApiOperation(value = "在我的博客中搜索")
    @GetMapping(path = "/search/{term}")
    public R searchInMyBlogs(@PathVariable int id,@PathVariable String term){
        return R.success();
    }

    @ApiOperation(value = "删除博客")
    @DeleteMapping(path = "/{blog_id}")
    public R deleteMyBlog(@PathVariable int blog_id){
        return R.success();
    }

    @ApiOperation(value = "创建博客")
    @PostMapping(path = "/{blog_id}")
    public R createMyBlog(@PathVariable int blog_id_id){
        return R.success();
    }

    @ApiOperation(value = "更新博客")
    @PutMapping(path = "/{blog_id}")
    public R updateMyBlog(@PathVariable int blog_id){
        return R.success();
    }
}
