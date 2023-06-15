package com.example.myblog.controller;

import com.example.myblog.common.R;
import com.example.myblog.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/homepage")
@Api(tags = "首页接口")
public class HomeController {
    @Autowired
    BlogService blogService;

    @ApiOperation(value = "热门博客列表")
    @GetMapping(path="/hotbloglist")
    public R hotbloglist(){

        return R.success(blogService.hotbloglist());
    }
    @ApiOperation(value = "最新博客列表")
    @GetMapping(path="/timebloglist")
    public R timebloglist(){
        return R.success(blogService.timebloglist());
    }
}
