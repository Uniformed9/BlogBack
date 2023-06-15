package com.example.myblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myblog.common.R;
import com.example.myblog.entity.Blog;
import com.example.myblog.mapper.BlogMapper;
import com.example.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Autowired
    BlogMapper blogMapper;
    @Override
    public List<Blog> hotbloglist() {
        LambdaQueryWrapper<Blog> queryWrapper=new LambdaQueryWrapper<>();
        //博客必须已经发表
        queryWrapper.eq(Blog::getPublished,true);
        //按浏览量降序排列
        queryWrapper.orderByDesc(Blog::getViews);
        //最多显示有限条
        Page<Blog> page=new Page();
        page(page,queryWrapper);

        List<Blog> Blogs=page.getRecords();
        return Blogs;
    }

    @Override
    public List<Blog> timebloglist() {
        LambdaQueryWrapper<Blog> queryWrapper=new LambdaQueryWrapper<>();
        //博客必须已经发表
        queryWrapper.eq(Blog::getPublished,true);
        //按浏览量降序排列
        queryWrapper.orderByDesc(Blog::getUpdateTime);
        //最多显示有限条
        Page<Blog> page=new Page();
        page(page,queryWrapper);

        List<Blog> Blogs=page.getRecords();

        return Blogs;
    }

    @Override
    public List<Blog> personalBlogs(int userId) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("user_id", userId);
        return blogMapper.selectByMap(columnMap);
    }

    @Override
    public List<Blog> allBlogs() {
        List<Blog> blogList = blogMapper.selectList(null);
        return blogList;
    }

    @Override
    public List<Blog> searchBlog(Time startTime, Time endTime, String searchTerm) {
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        if (startTime != null) wrapper.ge(Blog::getCreateTime, startTime);
        if (endTime != null) wrapper.le(Blog::getCreateTime, endTime);
        LambdaQueryWrapper<Blog> NickNameWrapper = wrapper.clone().like(Blog::getUserNickname, searchTerm);
        LambdaQueryWrapper<Blog> TitleWrapper = wrapper.clone().like(Blog::getTitle, searchTerm);
        LambdaQueryWrapper<Blog> DescriptionWrapper = wrapper.clone().like(Blog::getDescription, searchTerm);
        LambdaQueryWrapper<Blog> ContentWrapper = wrapper.clone().like(Blog::getContent, searchTerm);
        List<Blog> l1 = blogMapper.selectList(NickNameWrapper);
        List<Blog> l2 = blogMapper.selectList(TitleWrapper);
        List<Blog> l3 = blogMapper.selectList(DescriptionWrapper);
        List<Blog> l4 = blogMapper.selectList(ContentWrapper);
        List<Blog> all = Stream.of(l1, l2, l3, l4).flatMap(Collection::stream).collect(Collectors.toList());
//        System.out.println(all);
        List<Integer> index = new ArrayList<>();
        List<Blog> ret = new ArrayList<>();
        for (Blog blog : all) {
            int tempId = blog.getId();
            if (index.contains(tempId)) {
                continue;
            } else {
                index.add(tempId);
                ret.add(blog);
            }
        }
        return ret;
    }

    @Override
    public boolean isBlogExist(int id) {
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getId, id);
        return blogMapper.selectList(wrapper).size() > 0;
    }

    @Override
    public List<Blog> personalBlogsSearch(int id, Time startTime, Time endTime, String searchTerm) {
        List<Blog> list = searchBlog(startTime, endTime, searchTerm);
        List<Blog> ret = new ArrayList<>();
        for (Blog blog : list) {
            if (blog.getUserId() == id) {
                ret.add(blog);
            }
        }
        return ret;
    }

    @Override
    public boolean deleteBlog(int id) {
        return removeById(id);
    }

    @Override
    public void insertBlog(Blog blog) {
        blogMapper.insert(blog);
    }

    @Override
    public boolean modifyBlog(Blog blog) {
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getId, blog.getId());
        if (blogMapper.exists(wrapper)) {
            removeById(blog.getId());
            insertBlog(blog);
            return true;
        } else {
            return false;
        }
    }

}
