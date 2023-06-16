package com.example.myblog.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
public class Blog {
    private int id;
    private byte commentabled;
    //    @TableField("create_date")
    @JsonFormat(locale = "zh", timezone = "UTC+8", pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date createDate;
    private String content;
    @JsonFormat(pattern = "hh-mm-ss")
    private Time createTime;
    private String description;
    private byte published;
    private String title;
    @JsonFormat(locale = "zh", timezone = "UTC+8", pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date updateDate;
    //    @TableField("update_time")
    @JsonFormat(pattern = "hh-mm-ss")
    private Time updateTime;
    private int views;
    //    @TableField("user_id")
    private int userId;
    @TableField("user_nick_name")
    private String userNickname;
}
