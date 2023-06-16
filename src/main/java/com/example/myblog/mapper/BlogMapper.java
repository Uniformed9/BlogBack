package com.example.myblog.mapper;

import com.example.myblog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Mapper
public interface BlogMapper extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {

}
