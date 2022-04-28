package com.blog.vuemanger.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.vuemanger.mapper.BlogInfoMapper;
import com.blog.vuemanger.pojo.BlogInfo;
import com.blog.vuemanger.service.BlogInfoService;
import org.springframework.stereotype.Service;
/*
* 博客信息
* */
@Service
public class BlogInfoServiceImpl extends ServiceImpl<BlogInfoMapper, BlogInfo> implements BlogInfoService {
}
