package com.example.myblog.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myblog.entity.Blog;
import com.example.myblog.mapper.BlogMapper;
import com.example.myblog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {
    @Autowired
    BlogMapper blogMapper;

    @Override
    public List<Blog> personalBlogs(int userId) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("user_id", userId);
        return blogMapper.selectByMap(columnMap);
    }

    @Override
    public List<Blog> allBlogs() {
        return null;
    }

    @Override
    public List<Blog> searchBlog(Time startTime, Time endTime, String searchTerm) {
        return null;
    }

    @Override
    public List<Blog> personalBlogsSearch(int id, String searchTerm) {
        return null;
    }

    @Override
    public void deleteBlog(int id) {

    }

    @Override
    public void insertBlog(Blog blog) {

    }

    @Override
    public void modifyBlog(Blog blog) {

    }

}
