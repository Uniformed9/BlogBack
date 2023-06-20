package com.example.myblog.controller;

import com.example.myblog.common.R;
import com.example.myblog.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/homepage")
@Api(tags = "首页接口")
public class HomeController {
    @Autowired
    BlogService blogService;

    @ApiOperation(value = "热门博客列表")
    @GetMapping(path = "/hotBlogs")
    public R getHotBlogs() {
        return R.success(blogService.hotBlogList());
    }

    @ApiOperation(value = "最新博客列表")
    @GetMapping(path = "/newestBlogs")
    public R getNewestBlogs() {
        return R.success(blogService.newestBlogList());
    }

    @ApiOperation(value = "搜索")
    @GetMapping(path="/{term}")
    public R searchBlogs(@PathVariable String term){
        return R.success(blogService.searchBlog(term));
    }
}
