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

public class BlogType {
    private String blogTypename;

    public BlogType() {
    }

    public BlogType(String blogTypename) {
        this.blogTypename = blogTypename;
    }



    public String getBlogTypename() {
        return blogTypename;
    }

    public void setBlogTypename(String blogTypename) {
        this.blogTypename = blogTypename;
    }
    /*
    * 重写toString方法,只返回一类型
    * */
    @Override
    public String toString() {
        return blogTypename;
    }
}
