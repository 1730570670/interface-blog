package com.blog.vuemanger.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.vuemanger.common.R;
import com.blog.vuemanger.common.formatTime;
import com.blog.vuemanger.filter.LoginFilter;
import com.blog.vuemanger.pojo.Adminlogin;
import com.blog.vuemanger.service.AdminLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/*
* 登录接口
* */
@Slf4j
@RestController
@RequestMapping("/login")
@CrossOrigin
public class AdminLoginController {
    //注入邮箱发送者
    @Value("${vuemanger.emailfrom}")
    private String EmailForm;
    //注入邮箱发送
    @Autowired
    private JavaMailSenderImpl sender;
    //注入业务层
    @Autowired
    private AdminLoginService service;

    /**
     * 登录接口
     * @param request
     * @param adminLogin
     * @param mailMessage
     * @return
     */
    @PostMapping("/{userName}/{userPassword}")
    public R<String> login(HttpServletRequest request, Adminlogin adminLogin, SimpleMailMessage mailMessage){
        log.info("登录接口被请求");
        //创建Session
        HttpSession session = request.getSession();
        //密码(将获取的密码进行加密处理)
        String userPassword = DigestUtils.md5DigestAsHex(adminLogin.getUserPassword().getBytes());
        //条件构造器
        QueryWrapper<Adminlogin> queryWrapper=new QueryWrapper<>();
        //判断条件
        queryWrapper.eq("user_name",adminLogin.getUserName());
        queryWrapper.eq("user_password",userPassword);
        //条件查询
        Adminlogin userInfo = service.getOne(queryWrapper);
        //没有找到账号return
        if(userInfo==null){
            return R.error("账号或密码错误");
        }
        //发送邮箱
        mailMessage.setSubject("欢迎访问顾寒的blog管理系统");//发送标题
        String text="尊敬的"+userInfo.getUserName()+"你好,你在北京时间" +
                ""+ formatTime.getTime()+"访问blog管理系统,感谢您的支持";//发送的内容
        mailMessage.setText(text);//发送内容
        mailMessage.setTo(userInfo.getEmailAddress());//接收者
        mailMessage.setFrom(EmailForm);//发送者
        sender.send(mailMessage);//发送
        LoginFilter.grade=userInfo.getGrade();//更改登陆状态为登录
        return R.success((adminLogin.getUserName()));
    }

    /**
     * 退出登录接口
     * @param session
     * @return
     */
    @GetMapping
    public R<String> backLogin(HttpSession session){
        LoginFilter.grade=0;//将登陆状态变为0
        return R.success("退出成功");
    }
}
