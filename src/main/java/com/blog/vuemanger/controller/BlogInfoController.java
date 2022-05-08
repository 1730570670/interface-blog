package com.blog.vuemanger.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public R<Boolean> pubBlog(@RequestBody saveBlogInfo saveBlogInfo){
        int i = typeService.searchTypeName(saveBlogInfo.getBlogType());
        boolean isSave = blogService.saveMyBlog(saveBlogInfo.getBlogTitle(),
                i, saveBlogInfo.getBlogImgUrl(),
                saveBlogInfo.getBlogContent(),saveBlogInfo.getBlogOperate());
        return R.success(isSave);
    }
    /*
    * 根据ID进行查询信息
    * */
    @GetMapping("/blogId/{blogId}")
    public R<BlogInfo> searchByID(@PathVariable int blogId){
        BlogInfo blogInfo = blogService.searchByID(blogId);
        return R.success(blogInfo);
    }
    /*
    * 添加分类的接口
    * blogTypename 传来的分类名字参数
    * */
    @GetMapping("/saveType/{blogTypename}")
    public Boolean saveType(@PathVariable String blogTypename){
        //将传来的分类名查询,看是可否存在,
        QueryWrapper<BlogType> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("blog_typename",blogTypename);
        //查询看是否存在
        BlogType type = typeService.getOne(queryWrapper);
        //如果存在返回false
        if(type!=null){
            return false;
        }
        BlogType blogType=new BlogType();
        //讲穿来的分类名设置
        blogType.setBlogTypename(blogTypename);
        //将分类保存
        boolean save = typeService.save(blogType);
        return save;
    }
    /*
    * 修改分类
    * blogTypename 传来旧分类名字参数
    * newblogTypename 传来旧分类名字参数
    * */
    @GetMapping("/updateType/{blogTypename}/{newBlogTypename}")
    public Object updateBlogType(@PathVariable String blogTypename, @PathVariable String newBlogTypename){
        //将传来的分类名查询,看是可否存在,
        QueryWrapper<BlogType> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("blog_typename",newBlogTypename);
        //查询看是否存在
        BlogType type = typeService.getOne(queryWrapper);
        //如果存在返回false
        if(type!=null){
            return R.error(String.valueOf(false));
        }
        UpdateWrapper<BlogType> updateWrapper=new UpdateWrapper<>();
        //条件满足旧值
        updateWrapper.eq("blog_typename",blogTypename);
        //设置为新值
        updateWrapper.set("blog_typename",newBlogTypename);
        boolean update = typeService.update(updateWrapper);
        return R.success(update);
    }
    /*
    * 删除分类
    * */
    @GetMapping("/deleteType/{blogTypename}")
    public R<Boolean> deleteType(@PathVariable String blogTypename){
        Map<String,Object> map=new HashMap<>();
        map.put("blog_typename",blogTypename);
        boolean remove = typeService.removeByMap(map);
        return R.success(remove);
    }
}
