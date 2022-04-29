package com.blog.vuemanger.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.vuemanger.pojo.BlogInfo;

public interface BlogInfoService extends IService<BlogInfo> {
    Page<BlogInfo> searchBlogInfo(Page<BlogInfo> page);
}
