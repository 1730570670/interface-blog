package com.blog.vuemanger.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.vuemanger.pojo.BlogInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/*
* 博客信息
* */

@Mapper
@Repository
public interface BlogInfoMapper extends BaseMapper<BlogInfo> {
    //查询博客<分页>
    Page<BlogInfo> searchBlogInfo(Page<BlogInfo> page);
    //新增博客信息
    boolean saveMyBlog(String blogTitle,int blogType,String blogImgUrl,String blogContent,int opID);
}
