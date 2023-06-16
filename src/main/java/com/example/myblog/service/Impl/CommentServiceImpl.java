package com.example.myblog.service.Impl;

import com.example.myblog.entity.Blog;
import com.example.myblog.entity.Comment;
import com.example.myblog.mapper.CommentMapper;
import com.example.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    private List<Comment> tempReplys = new ArrayList<>();
    private BlogServiceImpl BlogServicelmpl;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by("createTime");
        List<Comment> comments = commentMapper.findByBlogIdAndParentCommentNull(blogId,sort);
        comments.forEach(comment -> {
            Blog blog = comment.getBlog();
            blog.setContent("");
            comment.setBlog(blog);
        });
        return comments;
    }


    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        return comment;
    }

    @Override
    public List<Comment> listComment() {
        List<Comment> comments = commentMapper.findAll();
        comments.forEach(comment -> {
            Blog blog = comment.getBlog();
            blog.setContent("");
            comment.setBlog(blog);
        });
        return comments;
    }

    @Override
    public List<String> CommentCountByMonth() {
        return commentMapper.CommentCountByMonth();
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentMapper.getOne(id);
    }

    @Override
    public void deleteComment(Long id) {
        BlogServicelmpl.deleteBlog(Math.toIntExact(id));
    }
}
