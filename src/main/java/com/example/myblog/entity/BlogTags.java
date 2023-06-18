package com.example.myblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogTags {
    @TableField("blogs_id")
    private int blogId;
    @TableField("tags_id")
    private int tagId;
}
