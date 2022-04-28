package com.blog.vuemanger.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
* 博客信息实体类
* */
@TableName("BlogInfo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogInfo {
    @TableId
    private Integer blogId;
    private String blogTitle;
    private String blogContent;
    private String blogImgUrl;
    private Date blogCreateTime;
    private String blogOperate;
    private BlogType blogType;
}
