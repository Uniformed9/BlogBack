package com.example.myblog.controller;

import com.example.myblog.common.R;
import com.example.myblog.entity.Blog;
import com.example.myblog.service.BlogService;
import com.example.myblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping(path = "/{id}")
    public R getBlog(@PathVariable int id) {
        if (!blogService.isBlogExist(id)) return R.error("Blog " + id + " is not exist!");
        return R.success(blogService.getBlogById(id));
    }

    @ApiOperation(value = "获取全部博客的内容")
    @GetMapping("/getAll")
    public R<List<Blog>> getBlogAll() {
        List<Blog> list = blogService.list();
        return R.success(list);
    }

    @ApiOperation(value = "获取博客标签")
    @GetMapping(path = "/{id}/tags")
    public R getBlogTags(@PathVariable int id) {
        if (!blogService.isBlogExist(id)) return R.error("Blog " + id + " is not exist!");
        return R.success(blogService.getTagsByBlogId(id));
    }

    @ApiOperation(value = "获取作者信息")
    @GetMapping(path = "/{id}/author")
    public R getAuthorInfo(@PathVariable int id) {
        return R.success(userService.selectUserByuserId(id));
    }

    @ApiOperation(value = "搜索")
    @GetMapping(path = "/search/{term}")
    public R search(@PathVariable String term){
        return R.success(blogService.searchBlog(term));
    }

    @ApiOperation(value = "浏览")
    @PostMapping(path = "/view/{id}")
    public R view(@PathVariable int id) {
        blogService.view(id);
        return R.success();
    }

    @ApiOperation(value = "浏览")
    @GetMapping(path = "/view/{id}")
    public R viewNum(@PathVariable int id) {
        return R.success(blogService.viewNum(id));
    }
}
