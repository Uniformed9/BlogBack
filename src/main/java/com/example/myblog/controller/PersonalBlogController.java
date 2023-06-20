package com.example.myblog.controller;

import com.example.myblog.common.R;
import com.example.myblog.entity.Blog;
import com.example.myblog.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/user/{id}/home/blogs")
@Api(tags = "个人博客接口")
public class PersonalBlogController {
    @Autowired
    BlogService blogService;

    @ApiOperation(value = "我的博客")
    @GetMapping()
    public R myBlogs(@PathVariable int id) {
        return R.success(blogService.personalBlogs(id));
    }

    @ApiOperation(value = "在我的博客中搜索")
    @GetMapping(path = "/search/{term}")
    public R searchInMyBlogs(@PathVariable int id, @PathVariable String term) {
        return R.success(blogService.personalBlogsSearch(id, term));
    }

    @ApiOperation(value = "删除博客")
    @DeleteMapping(path = "/{blog_id}")
    public R deleteMyBlog(@PathVariable int blog_id) {
        return R.success(blogService.deleteBlog(blog_id));
    }

//    @ApiOperation(value = "创建博客")
//    @PostMapping()
//    public R createMyBlog(@PathVariable int id, @RequestBody Blog blog) {
//        if (blogService.isBlogExist(blog.getId())) {
//            return R.error("Blog is exist, cannot create an exist blog.");
//        } else {
//            blog.setUserId(id);
//            blogService.insertBlog(blog);
//        }
//        return R.success();
//    }

    @ApiOperation(value = "更新博客")
    @PutMapping("/{blog_id}")
    public R updateMyBlog(@PathVariable int blog_id, @RequestBody Map<String, String> bm) {
        Blog blog = blogService.getBlogById(blog_id);
        blog.setContent(bm.get("content"));
        blog.setDescription(bm.get("description"));
        blog.setTitle(bm.get("title"));
        blogService.insertBlog(blog);
        return R.success();
    }

    @ApiOperation(value = "创建博客")
    @PostMapping()
    public R createMyBlog(@PathVariable int id, @RequestBody Map<String, String> bm) {
        Blog blog = new Blog();
        blog.setUserId(id);
        blog.setContent(bm.get("content"));
        blog.setDescription(bm.get("description"));
        blog.setTitle(bm.get("title"));
        blog.setViews(0);
        blog.setPublished((byte) 1);
        blog.setCommentabled((byte) 1);
        blogService.insertBlog(blog);
        return R.success();
    }
}
