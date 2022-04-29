package com.blog.vuemanger.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.vuemanger.pojo.BlogType;
/*
* 分类
* */
public interface BlogTypeService extends IService<BlogType> {
    int searchTypeName(String typeName);
}
