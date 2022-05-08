package com.blog.vuemanger.controller;

import com.blog.vuemanger.service.AdmininfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
/*
* 个人信息控制层
* */
@RestController
public class AdminInfoController {

    @Autowired
    private AdmininfoService admininfoService;

}
