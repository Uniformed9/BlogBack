package com.example.myblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myblog.entity.Blog;
import com.example.myblog.entity.BlogTags;
import com.example.myblog.entity.Tag;
import com.example.myblog.mapper.BlogMapper;
import com.example.myblog.mapper.BlogTagsMapper;
import com.example.myblog.mapper.TagMapper;
import com.example.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    BlogTagsMapper blogTagsMapper;
    @Autowired
    TagMapper tagMapper;


    @Override
    public List<Blog> hotBlogList() {
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Blog::getPublished, true);
        queryWrapper.orderByDesc(Blog::getViews);
        Page<Blog> page = new Page();
        page(page, queryWrapper);
        List<Blog> Blogs = page.getRecords();
        return Blogs;
    }

    @Override
    public List<Blog> newestBlogList() {
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Blog::getPublished, true);
        queryWrapper.orderByDesc(Blog::getUpdateTime);
        Page<Blog> page = new Page();
        page(page, queryWrapper);
        List<Blog> Blogs = page.getRecords();
        return Blogs;
    }

    @Override
    public List<Tag> getTagsByBlogId(int blogId) {
        List<BlogTags> l = blogTagsMapper.selectList(new LambdaQueryWrapper<BlogTags>().eq(BlogTags::getBlogId, blogId));
        if (l == null || l.size() == 0) return null;
        List<Tag> tl = new ArrayList<>();
        for (BlogTags blogTags : l) {
            int tid = blogTags.getTagId();
            tl.add(tagMapper.selectOne(new LambdaQueryWrapper<Tag>().eq(Tag::getId, tid)));
        }
        return tl;
    }

    @Override
    public List<Blog> personalBlogs(int userId) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("user_id", userId);
        return blogMapper.selectByMap(columnMap);
    }

    @Override
    public List<Blog> allBlogs() {
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getPublished, true);
        List<Blog> blogList = blogMapper.selectList(null);
        return blogList;
    }

    @Override
    public List<Blog> searchBlog(String searchTerm) {
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(Blog::getPublished, true);
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
//        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(Blog::getId, id);
        return blogMapper.selectList(new LambdaQueryWrapper<Blog>().eq(Blog::getId, id)).size() > 0;
    }

    @Override
    public Blog getBlogById(int id) {
//        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(Blog::getId, id);
        return blogMapper.selectOne(new LambdaQueryWrapper<Blog>().eq(Blog::getId, id));
    }

    @Override
    public List<Blog> personalBlogsSearch(int id, String searchTerm) {
        List<Blog> list = searchBlog(searchTerm);
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
        if (!isBlogExist(id)) return false;
        return removeById(id);
    }

    @Override
    public int insertBlog(Blog blog) {
        blogMapper.insert(blog);
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getUserId,blog.getUserId());
        wrapper.eq(Blog::getTitle,blog.getTitle());
        wrapper.eq(Blog::getDescription,blog.getDescription());
        return blogMapper.selectOne(wrapper).getId();

    }

    @Override
    public boolean updateBlog(Blog blog) {
        return blogMapper.updateById(blog) >= 0;
    }

    @Override
    public boolean deleteTag(int blogId, int tagId) {
        LambdaQueryWrapper<BlogTags> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogTags::getTagId,tagId);
        wrapper.eq(BlogTags::getBlogId,blogId);
        if(blogTagsMapper.exists(wrapper)){
            blogTagsMapper.delete(wrapper);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void addTag(int blogId, int tagId) {
        BlogTags blogTags = new BlogTags();
        blogTags.setBlogId(blogId);
        blogTags.setTagId(tagId);
        blogTagsMapper.insert(blogTags);
    }
}
