package com.blog.vuemanger.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.vuemanger.common.R;
import com.blog.vuemanger.pojo.BlogInfo;
import com.blog.vuemanger.pojo.BlogType;
import com.blog.vuemanger.pojo.saveBlogInfo;
import com.blog.vuemanger.service.BlogInfoService;
import com.blog.vuemanger.service.BlogTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/blog")

public class BlogInfoController {
    @Autowired
    private BlogInfoService blogService;

    @Autowired
    private BlogTypeService typeService;

    /*
    * 分页查询接口,分页查询,接收参数为查询第几页(一页八个内容)
    * */
    @PostMapping("/{pageCurrent}")
    public R<Page<BlogInfo>> blogInfos(@PathVariable int pageCurrent){
        //设置分页数量为一页八条数据,第几页为参数
        Page<BlogInfo> page=new Page<>(pageCurrent,8);
        Page<BlogInfo> infoPage = blogService.searchBlogInfo(page);
        System.out.println(page.getPages());
        System.out.println(infoPage);
        return R.success(infoPage);
    }
    /*
    * 查询博客分类接口
    * */
    @GetMapping("/type")
    public R<List<BlogType>> blogType(){
        List<BlogType> types = typeService.list();
        return R.success(types);
    }
    /*
    * 发布博客接口
    * */
    @PostMapping(path = "/save")
    public R<String> pubBlog(@RequestBody saveBlogInfo saveBlogInfo){
        int i = typeService.searchTypeName(saveBlogInfo.getBlogType());
        boolean isSave = blogService.saveMyBlog(saveBlogInfo.getBlogTitle(),
                i, saveBlogInfo.getBlogImgUrl(),
                saveBlogInfo.getBlogContent(),saveBlogInfo.getBlogOperate());
        return R.success("数据");
    }

}
