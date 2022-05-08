package com.blog.vuemanger.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.vuemanger.common.R;
import com.blog.vuemanger.pojo.FrontInfo;
import com.blog.vuemanger.pojo.MangerInfo;
import com.blog.vuemanger.service.FrontinfoService;
import com.blog.vuemanger.service.MangerinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
* 针对于用户访问记录,将记录存在于数据库当中
* 前台,后台的控制层合并于此
* */
@RestController
@RequestMapping("/ipAddress")
public class IpAddressController {


    @Autowired
    private FrontinfoService frontinfoService;//前台业务层

    @Autowired
    private MangerinfoService mangerinfoService;//后天业务层


    /**
     * ipAddress IP地址,areaAddress 地区
     * @param frontInfo
     * @return
     */
    @GetMapping
    public R<String> front(@RequestBody FrontInfo frontInfo){
        boolean save = frontinfoService.save(frontInfo);
        return R.success("添加访客记录成功");
    }

    /**
     * ipAddress IP地址,areaAddress 地区
     * @param mangerInfo
     * @return
     */
    @PostMapping
    public R<String> manger(@RequestBody MangerInfo mangerInfo){
        boolean save = mangerinfoService.save(mangerInfo);
        return R.success("添加访客记录成功");
    }

    /**
     * 查询所有前台的访客记录
     * @param pageCurrent
     * @return
     */
    @GetMapping("/front/{pageCurrent}")
    public R<Page<FrontInfo>> frontVisitor(@PathVariable int pageCurrent){
        //一页十个数量
        Page<FrontInfo> page=new Page<>(pageCurrent,10);
        //查询出来后降序排序
        QueryWrapper<FrontInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("visitor_time");
        Page<FrontInfo> pageInfo = frontinfoService.page(page, queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 查询所有后台的访客记录
     * @param pageCurrent
     * @return
     */
    @GetMapping("/manger/{pageCurrent}")
    public R<Page<MangerInfo>> mangerVisitor(@PathVariable int pageCurrent){
        //一页十个数量
        Page<MangerInfo> page=new Page<>(pageCurrent,10);
        QueryWrapper<MangerInfo> queryWrapper=new QueryWrapper<>();
        //按照事件的降序进行排序
        queryWrapper.orderByDesc("visitor_time");
        Page<MangerInfo> pageInfo = mangerinfoService.page(page, queryWrapper);
        return R.success(pageInfo);
    }
}
