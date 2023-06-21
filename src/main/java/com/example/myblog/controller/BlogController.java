package com.example.myblog.controller;

import com.example.myblog.common.R;
import com.example.myblog.service.BlogService;
import com.example.myblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/blog")
@Api(tags = "单个博客接口")
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    UserService userService;

    @ApiOperation(value = "获取全部博客")
    @GetMapping("/search/")
    public R getAllBlogs(){
        return R.success(blogService.list());
    }

    @ApiOperation(value = "获取博客的内容")
    @GetMapping()
    public R getBlog(@RequestBody int id) {
        if (!blogService.isBlogExist(id)) return R.error("Blog " + id + " is not exist!");
        return R.success(blogService.getBlogById(id));
    }

    @ApiOperation(value = "获取博客标签")
    @GetMapping(path = "/tags")
    public R getBlogTags(@RequestBody int id) {
        if (!blogService.isBlogExist(id)) return R.error("Blog " + id + " is not exist!");
        return R.success(blogService.getTagsByBlogId(id));
    }

    @ApiOperation(value = "获取作者信息")
    @GetMapping(path = "/author")
    public R getAuthorInfo(@RequestBody int id) {
        return R.success(userService.selectUserByuserId(id));
    }

    @ApiOperation(value = "搜索")
    @GetMapping(path = "/search/{term}")
    public R search(@PathVariable String term){
        return R.success(blogService.searchBlog(term));
    }

}
