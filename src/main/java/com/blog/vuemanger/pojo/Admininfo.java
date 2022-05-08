package com.blog.vuemanger.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName admininfo
 */
@TableName(value ="admininfo")
@Data
public class Admininfo implements Serializable {
    /**
     * 
     */
    private String adminName;

    /**
     * 
     */
    private String penguin;

    /**
     * 
     */
    private String weChat;

    /**
     * 
     */
    private String eMail;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}