package com.blog.vuemanger.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("BlogInfo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class saveBlogInfo {
    @TableId
    private String blogTitle;
    private String blogContent;
    private String blogImgUrl;
    private String blogType;
    private int blogOperate;
}
