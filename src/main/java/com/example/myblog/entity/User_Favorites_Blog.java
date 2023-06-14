package com.example.myblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User_Favorites_Blog {
    private int id;
    @TableField("uid")
    private int userId;
    @TableField("bid")
    private int blogId;
}
