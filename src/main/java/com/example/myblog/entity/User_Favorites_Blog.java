package com.example.myblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User_Favorites_Blog {
    private int id;
    @TableField("user_id")
    private int userId;
    @TableField("favorites_id")
    private int favoritesId;
    @TableField("blog_id")
    private int blogId;
}
