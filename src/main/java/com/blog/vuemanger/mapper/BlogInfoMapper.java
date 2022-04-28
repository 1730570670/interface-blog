package com.blog.vuemanger.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.vuemanger.pojo.BlogInfo;
import org.apache.ibatis.annotations.Mapper;

/*
* 博客信息
* */
@Mapper
public interface BlogInfoMapper extends BaseMapper<BlogInfo> {

}
