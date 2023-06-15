package com.example.myblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Favorites {
    private int id;
    @TableField("user_id")
    private int userId;
    private String name;

}
