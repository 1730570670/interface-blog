package com.blog.vuemanger.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.vuemanger.pojo.BlogInfo;

public interface BlogInfoService extends IService<BlogInfo> {
    /*
    * 分页插件
    * */
    Page<BlogInfo> searchBlogInfo(Page<BlogInfo> page);
    /*
    * 新增博客信息
    * */
    boolean saveMyBlog(String blogTitle,String blogType,String blogImgUrl,String blogContent,int opID);
}
