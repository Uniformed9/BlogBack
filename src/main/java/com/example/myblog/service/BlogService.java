package com.example.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myblog.entity.Blog;
import com.example.myblog.entity.Tag;

import java.sql.Time;
import java.util.List;

public interface BlogService extends IService<Blog> {

    List<Blog> allBlogs();

    /**
     * 时间范围内搜索博客
     *
     * @param startTime  起始时间
     * @param endTime    末尾时间
     * @param searchTerm 搜索词
     * @return 博客列表
     */
    List<Blog> searchBlog(Time startTime, Time endTime, String searchTerm);

    /**
     * 搜索单个博主的博客
     *
     * @param id         博主id
     * @param startTime  起始时间
     * @param endTime    末尾时间
     * @param searchTerm 搜索词
     * @return 博客列表
     */
    List<Blog> personalBlogsSearch(int id, Time startTime, Time endTime, String searchTerm);

    /**
     * 博主的所有博客
     *
     * @param id 博主id
     */
    List<Blog> personalBlogs(int id);

    /**
     * 删除博客
     */
    boolean deleteBlog(int id);

    /**
     * 插入博客
     */
    void insertBlog(Blog blog);

    /**
     * 修改博客
     */
    boolean updateBlog(Blog blog);

    /**
     * 博客是否存在
     */
    boolean isBlogExist(int id);

    /**
     * 根据id获取博客
     *
     * @param id 博客id
     */
    Blog getBlogById(int id);

    /**
     * 获取博客的标签
     *
     * @param id 博客id
     */
    List<Tag> getTagsByBlogId(int id);

    /**
     * 热门博客
     */
    List<Blog> hotbloglist();

    /**
     * 最新博客
     */
    List<Blog> newestBlogList();
}

    List<Blog> hotbloglist();

    List<Blog>  timebloglist();
}