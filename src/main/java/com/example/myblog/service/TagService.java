package com.example.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myblog.entity.Blog;
import com.example.myblog.entity.Tag;

import java.util.List;

public interface TagService extends IService<Tag> {

    /**
     * 插入tag
     *
     * @param tag
     */
    void insertTag(Tag tag);

    /**
     * 根据标签id搜索tag
     *
     * @param id 标签id
     * @return
     */
    Tag selectById(int id);

    /**
     * 根据tag名字搜索tag
     *
     * @param name tag名
     * @return
     */
    Tag selectByName(String name);

    /**
     * 获取拥有此标签的所有博客
     *
     * @param id 标签id
     * @return
     */
    List<Blog> getBlogByTagId(int id);

    /**
     * 获取拥有此标签的所有博客
     *
     * @param name 标签名
     * @return
     */
    List<Blog> getBlogByTagName(String name);

}
