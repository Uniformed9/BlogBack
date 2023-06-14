package com.example.myblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Blog_Tag_Relation {
    private int id;
    @TableField("bid")
    private int blogId;
    @TableField("tid")
    private int tagId;
}
