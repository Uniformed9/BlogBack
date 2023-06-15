package com.example.myblog.service;


import com.example.myblog.entity.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BlogServiceTest {

    @Autowired
    BlogService blogService;

    @Test
    void allBlogs() {
        System.out.println(blogService.allBlogs());
    }

    @Test
    void searchBlog() {
        System.out.println(blogService.searchBlog(null, null, "test"));
    }

    @Test
    void personalBlogsSearch() {
        System.out.println(blogService.personalBlogsSearch(1, null, null, "test"));
    }

    @Test
    void personalBlogs() {
        System.out.println(blogService.personalBlogs(1));
    }

    @Test
    void deleteBlog() {
        System.out.println(blogService.deleteBlog(1));
    }

    @Test
    void insertBlog() {
        Blog blog = new Blog();
        blog.setUserId(1);
        blogService.insertBlog(blog);
    }

    @Test
    void modifyBlog() {
        Blog blog = new Blog();
        blog.setId(1);
        blog.setUserId(1);
        blog.setContent("test");
        System.out.println(blogService.updateBlog(blog));
    }

    @Test
    void isBlogExist() {
        System.out.println(blogService.isBlogExist(1));
    }

    @Test
    void getBlogById() {
        System.out.println(blogService.getBlogById(1));
    }

    @Test
    void getTagsByBlogId() {
        System.out.println(blogService.getTagsByBlogId(1));
    }
}
