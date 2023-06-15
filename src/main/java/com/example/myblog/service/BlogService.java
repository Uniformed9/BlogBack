package com.example.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myblog.common.R;
import com.example.myblog.entity.Blog;

import java.sql.Time;
import java.util.List;

public interface BlogService extends IService<Blog> {

    List<Blog> allBlogs();

    List<Blog> searchBlog(Time startTime, Time endTime, String searchTerm);

    List<Blog> personalBlogsSearch(int id, Time startTime, Time endTime, String searchTerm);

    List<Blog> personalBlogs(int id);

    boolean deleteBlog(int id);

    void insertBlog(Blog blog);

    boolean modifyBlog(Blog blog);

    boolean isBlogExist(int id);

    List<Blog> hotbloglist();

    List<Blog>  timebloglist();
}