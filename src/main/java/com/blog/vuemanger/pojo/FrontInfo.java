package com.blog.vuemanger.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @TableName frontinfo
 */
@TableName(value ="frontinfo")
@Data
public class FrontInfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer frontId;

    /**
     * 前台访客IP地址
     */
    private String ipAddress;

    /**
     * 前台访客地理位置
     */
    private String areaAddress;
    /**
    * 前台访问的时间
    */
    private Date visitorTime;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}