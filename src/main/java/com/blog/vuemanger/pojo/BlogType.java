package com.blog.vuemanger.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
* 分类实体类
* */
@TableName("BlogType")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogType {
    @TableId
    private Integer blogType;
    private String blogTypename;
}
