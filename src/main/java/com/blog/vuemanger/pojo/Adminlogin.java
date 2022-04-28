package com.blog.vuemanger.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("AdminLogin")
public class Adminlogin {

    private long id;

    private String userName;

    private String userPassword;

    private String emailAddress;

    private Date createTime;

    private int grade;

}
