package com.example.myblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myblog.entity.Blog;
import com.example.myblog.entity.BlogTags;
import com.example.myblog.entity.Tag;
import com.example.myblog.mapper.BlogMapper;
import com.example.myblog.mapper.BlogTagsMapper;
import com.example.myblog.mapper.TagMapper;
import com.example.myblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Autowired
    BlogTagsMapper blogTagsMapper;

    @Autowired
    BlogMapper blogMapper;

    @Override
    public void insertTag(Tag tag) {
        tagMapper.insert(tag);
    }

    @Override
    public Tag selectById(int id) {
        List<Tag> l = tagMapper.selectList(new LambdaQueryWrapper<Tag>().eq(Tag::getId, id));
        if (l == null || l.size() == 0) return null;
        return l.get(0);
    }

    @Override
    public Tag selectByName(String name) {
        List<Tag> l = tagMapper.selectList(new LambdaQueryWrapper<Tag>().eq(Tag::getName, name));
        if (l == null || l.size() == 0) return null;
        return l.get(0);
    }

    @Override
    public List<Blog> getBlogByTagId(int tagId) {
        List<BlogTags> l = blogTagsMapper.selectList(new LambdaQueryWrapper<BlogTags>().eq(BlogTags::getTagId, tagId));
        if (l == null || l.size() == 0) return null;
        List<Blog> bl = new ArrayList<>();
        for (BlogTags blogTags : l) {
            int bid = blogTags.getBlogId();
            bl.add(blogMapper.selectOne(new LambdaQueryWrapper<Blog>().eq(Blog::getId, bid)));
        }
        return bl;
    }

    @Override
    public List<Blog> getBlogByTagName(String name) {
        int id = tagMapper.selectOne(new LambdaQueryWrapper<Tag>().eq(Tag::getName,name)).getId();
        return getBlogByTagId(id);
    }
}
