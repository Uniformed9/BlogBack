package com.example.myblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
public class Blog {
    private int id;
    private byte commentabled;
    private String content;
    @TableField("create_time")
    private Time createTime;
    private String description;
    private byte published;
    private String title;
    @TableField("update_time")
    private Time updateTime;
    private int views;
    @TableField("user_id")
    private int userId;
}
