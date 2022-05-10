package com.blog.vuemanger.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.blog.vuemanger.common.R;
import com.blog.vuemanger.pojo.Admininfo;
import com.blog.vuemanger.service.AdmininfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
* 个人信息控制层
* */
@RestController
@RequestMapping("/AdminInfo")
public class AdminInfoController {

    @Autowired
    private AdmininfoService admininfoService;

    /**
     *
     * @return 查询管理员在前端显示的信息
     */
    @GetMapping
    public R<Admininfo> GetInfo(){
        Admininfo one = admininfoService.getOne(null);
        return R.success(one);
    }

    /**
     * 修改信息Restful风格
     * @param userName
     * @param penguin
     * @param weChat
     * @param email
     * @return
     */
    @PostMapping("/{userName}/{penguin}/{weChat}/{email}")
    public R<Boolean> updateInfo(@PathVariable String userName,
                                 @PathVariable String penguin,
                                 @PathVariable String weChat,
                                 @PathVariable String email){
        //创建修改构造器
        UpdateWrapper<Admininfo> updateWrapper=new UpdateWrapper<>();
        updateWrapper.set("user_name",userName);
        updateWrapper.set("penguin",penguin);
        updateWrapper.set("we_chat",weChat);
        updateWrapper.set("e_mail",email);
        //将信息修修改
        boolean update = admininfoService.update(updateWrapper);
        return R.success(update);
    }
}
