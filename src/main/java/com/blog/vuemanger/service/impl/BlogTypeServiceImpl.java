package com.blog.vuemanger.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.vuemanger.mapper.BlogTypeMapper;
import com.blog.vuemanger.pojo.BlogType;
import com.blog.vuemanger.service.BlogTypeService;
import org.springframework.stereotype.Service;

@Service
public class BlogTypeServiceImpl extends ServiceImpl<BlogTypeMapper, BlogType> implements BlogTypeService {
}
