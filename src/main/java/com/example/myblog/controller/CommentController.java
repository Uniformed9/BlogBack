package com.example.myblog.controller;

import com.example.myblog.entity.Comment;
import com.example.myblog.entity.Result;
import com.example.myblog.entity.StatusCode;
import com.example.myblog.entity.User;
import com.example.myblog.service.BlogService;
import com.example.myblog.service.CommentService;
import com.example.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;


@RestController
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    private final String avatar = "/images/avatar.png";

    //获取评论集合
    @GetMapping("/comments/{blogId}")
    public Result comments(@PathVariable Long blogId) {
        return new Result(true, StatusCode.OK, "获取博客评论成功", commentService.listCommentByBlogId(blogId));
    }

    @PostMapping("/comments")
    public Result post(@RequestBody Map<String,Object> para) {
        System.out.println(para);
        String content= (String) para.get("content");
        Long blogId= Long.parseLong( para.get("blogId").toString());
        Long userId= Long.parseLong( para.get("userId").toString());
        long parentId= Long.parseLong( para.get("parentId").toString());
        User user = userService.selectUserByuserId(Math.toIntExact(userId));
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setBlog(blogService.getBlogById(Math.toIntExact(blogId)));
        comment.setUserId(userId);
        comment.setNickname(user.getNickName());
        comment.setEmail(user.getEmail());
        comment.setAvatar(user.getAvatar());
        comment.setAdminComment(Objects.equals(user.getType(), 1));
        if (parentId != -1){
            comment.setParentComment(commentService.getCommentById(parentId));
        }
        System.out.println(comment);
        Comment newComment = commentService.saveComment(comment);
        return new Result(true,StatusCode.OK,"评论发表成功！",newComment);
    }

    //删除评论
    @GetMapping("/comments/{id}/delete")
    public Result delete(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new Result(true, StatusCode.OK, "删除评论成功", null);
    }

}
