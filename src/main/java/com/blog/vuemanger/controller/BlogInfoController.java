package com.blog.vuemanger.controller;

import com.blog.vuemanger.pojo.BlogInfo;
import com.blog.vuemanger.service.BlogInfoService;
import com.blog.vuemanger.service.BlogTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Slf4j
@RequestMapping("/blog")

public class BlogInfoController {
    @Autowired
    private BlogInfoService service;
    @GetMapping
    public String blogInfos(){
        return "dasdasdasdas";
    }
}
