package com.blog.vuemanger.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.vuemanger.pojo.BlogType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BlogTypeMapper extends BaseMapper<BlogType> {
    Integer searchTypeName(String typeName);
}
