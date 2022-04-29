package com.blog.vuemanger.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
* 发布博客是需要的实体类
* */
@TableName("BlogInfo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveBlogInfo {

    @TableId
    private Integer blogId;
    private String blogTitle;
    private String blogContent;
    private String blogImgUrl;
    private Date blogCreateTime;
    private Integer blogType;
    private String blogOperate;
}
