package com.example.myblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Blog {
    private int id;
    private int uid;
    private String path;
    @TableLogic
    @TableField("is_deleted")
    private boolean isDelete;
}
