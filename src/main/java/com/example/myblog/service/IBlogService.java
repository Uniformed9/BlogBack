package com.example.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myblog.entity.Blog;

import java.sql.Time;
import java.util.List;

public interface IBlogService extends IService<Blog> {

    List<Blog> allBlogs();

    List<Blog> searchBlog(Time startTime, Time endTime, String searchTerm);

    List<Blog> personalBlogsSearch(int id, String searchTerm);

    List<Blog> personalBlogs(int id);

    void deleteBlog(int id);

    void insertBlog(Blog blog);

    void modifyBlog(Blog blog);
}
