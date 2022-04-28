package com.blog.vuemanger.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.vuemanger.mapper.AdminLoginMapper;
import com.blog.vuemanger.pojo.Adminlogin;
import com.blog.vuemanger.service.AdminLoginService;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginServiceImpl extends ServiceImpl<AdminLoginMapper, Adminlogin> implements AdminLoginService {
}
