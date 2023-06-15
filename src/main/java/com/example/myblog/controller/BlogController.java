package com.example.myblog.controller;

import com.example.myblog.common.R;
import com.example.myblog.service.IBlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/blog/{id}")
@Api(tags = "博客接口")
public class BlogController {

    @ApiOperation(value = "获取博客的内容")
    @GetMapping()
    public R getBlog(@PathVariable int id) {

        return R.success();
    }

    @ApiOperation(value = "获取博客标签")
    @GetMapping(path = "/tags")
    public R getBlogTags(@PathVariable int id) {
        return R.success();
    }

    @ApiOperation(value = "获取作者信息")
    @GetMapping(path = "/author")
    public R getAuthorInfo(@PathVariable int id) {
        return R.success();
    }

    @ApiOperation(value = "收藏")
    @PostMapping()
    public R favoriteBlog(@RequestBody int userId, @PathVariable int id) {
        return R.success();
    }


}
