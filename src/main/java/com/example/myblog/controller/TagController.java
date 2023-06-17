package com.example.myblog.controller;

import com.example.myblog.common.R;
import com.example.myblog.entity.Tag;
import com.example.myblog.service.TagService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/tag")
@Api(tags = "标签接口")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping()
    public R tagList(){
        return R.success(tagService.list());
    }
}
