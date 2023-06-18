package com.example.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myblog.entity.BlogTags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogTagsMapper extends BaseMapper<BlogTags> {
//    @Select("select * from `blog_tags` where blogs_id = #{blogId}")
//    List<BlogTags> selectByBlogId(int blogId);
//
//    @Select("select * from `blog_tags` where tags_id = #{tagId}")
//    List<BlogTags> selectByTagId(int tagId);


}
