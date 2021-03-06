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
    /**
     *分页显示博客数据  查询博客信息
     * @param pageCurrent
     * @return
     */
    @PostMapping("/{pageCurrent}")
    public R<Page<BlogInfo>> blogInfos(@PathVariable int pageCurrent){
        //设置分页数量为一页六条数据,第几页为参数
        Page<BlogInfo> page=new Page<>(pageCurrent,8);
        Page<BlogInfo> infoPage = blogService.searchBlogInfo(page);
        return R.success(infoPage);
    }

    /**
     * 查询博客分类接口
     * @return
     */
    @GetMapping("/type")
    public R<List<BlogType>> blogType(){
        List<BlogType> types = typeService.list();
        return R.success(types);
    }

    /**
     * 发布博客接口
     * blogTitle 标题,blogContent 内容,blogImgUrl 头像地址(链接),
     * blogType(分类,前端传int类型),blogOperate 操作者(可选 必须 int)
     * @param saveBlogInfo
     * @return
     */
    @PostMapping(path = "/save")
    public R<Boolean> pubBlog(@RequestBody saveBlogInfo saveBlogInfo){
        int i = typeService.searchTypeName(saveBlogInfo.getBlogType());
        boolean isSave = blogService.saveMyBlog(saveBlogInfo.getBlogTitle(),
                i, saveBlogInfo.getBlogImgUrl(),
                saveBlogInfo.getBlogContent(),saveBlogInfo.getBlogOperate());
        return R.success(isSave);
    }

    /**
     * 修改博客内容
     * blogTitle 标题,blogType 分类,blogImgUrl 头像地址,blogContent 文章内容
     * blogId 博客ID
     * @param blogInfo
     * @return
     */
    @PostMapping(path = "/updateBlog/{blogId}")
    public R<Boolean> updateBlog(@RequestBody saveBlogInfo blogInfo, @PathVariable String blogId){
        //前端传来的值为String(多表联查),而主表为int关联,根据名字查询分类ID
        int type = typeService.searchTypeName(blogInfo.getBlogType());//查询分类ID
        UpdateWrapper<BlogInfo> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("blog_id",blogId);//对应条件,根据ID进行修改
        updateWrapper.set("blog_title",blogInfo.getBlogTitle());//标题
        updateWrapper.set("blog_img_url",blogInfo.getBlogImgUrl());//图片地址
        updateWrapper.set("blog_content",blogInfo.getBlogContent());//文章内容
        updateWrapper.set("blog_type",type);//分类(int)
        boolean update = blogService.update(updateWrapper);//修改状态
        return R.success(update);
    }

    /**
     * 根据ID进行查询博客信息
     * @param blogId
     * @return
     */
    @GetMapping("/blogId/{blogId}")
    public R<BlogInfo> searchByID(@PathVariable int blogId){
        BlogInfo blogInfo = blogService.searchByID(blogId);
        return R.success(blogInfo);
    }

    /**
     * 根据ID对博客信息进行删除
     * @param blogId 前端传入ID信息
     * @return
     */
    @PostMapping("/deleteBlog/{blogId}")
    public R<Boolean> deleteBlogById(@PathVariable int blogId){
        boolean b = blogService.removeById(blogId);//对信息进行删除
        return R.success(b);
    }

    /**
     * 添加分类的接口
     * @param blogTypename 需要添加的分类名
     * @return
     */
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

    /**
     * 修改分类
     * @param blogTypename 旧分类名
     * @param newBlogTypename  新分类名
     * @return
     */
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

    /**根据分类名删除分类
     *
     * @param blogTypename
     * @return
     */
    @GetMapping("/deleteType/{blogTypename}")
    public R<Boolean> deleteType(@PathVariable String blogTypename){
        Map<String,Object> map=new HashMap<>();
        map.put("blog_typename",blogTypename);
        boolean remove = typeService.removeByMap(map);
        return R.success(remove);
    }

}
