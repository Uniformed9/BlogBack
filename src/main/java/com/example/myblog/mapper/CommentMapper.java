package com.example.myblog.mapper;

import com.example.myblog.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentMapper extends JpaRepository<Comment,Long> {
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);

    @Query("select function('date_format',c.createTime, '%Y-%m') AS MONTH,count(c) as comment from Comment c group by MONTH ")
    List<String> CommentCountByMonth();


}
