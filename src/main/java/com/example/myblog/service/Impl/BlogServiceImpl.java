package com.example.myblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myblog.common.R;
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
    public R hotbloglist() {
        LambdaQueryWrapper<Blog> queryWrapper=new LambdaQueryWrapper<>();
        //博客必须已经发表
        queryWrapper.eq(Blog::getPublished,true);
        //按浏览量降序排列
        queryWrapper.orderByDesc(Blog::getViews);
        //最多显示有限条
        Page<Blog> page=new Page();
        page(page,queryWrapper);

        List<Blog> Blogs=page.getRecords();

        return R.success(Blogs);
    }

    @Override
    public R timebloglist() {
        LambdaQueryWrapper<Blog> queryWrapper=new LambdaQueryWrapper<>();
        //博客必须已经发表
        queryWrapper.eq(Blog::getPublished,true);
        //按浏览量降序排列
        queryWrapper.orderByDesc(Blog::getUpdateTime);
        //最多显示有限条
        Page<Blog> page=new Page();
        page(page,queryWrapper);

        List<Blog> Blogs=page.getRecords();

        return R.success(Blogs);
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
