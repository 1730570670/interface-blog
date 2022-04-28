package com.blog.vuemanger.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.vuemanger.pojo.BlogInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/*
* 博客信息
* */

@Mapper
@Component
public interface BlogInfoMapper extends BaseMapper<BlogInfo> {
    //查询博客<分页>
    Page<BlogInfo> searchBlogInfo(Page<BlogInfo> page);
}
