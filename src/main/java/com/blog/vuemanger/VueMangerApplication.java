package com.blog.vuemanger;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.blog.vuemanger.mapper")//开启Mapper包扫描
@Slf4j
@ServletComponentScan
public class VueMangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueMangerApplication.class, args);
        log.info("项目成功运行");
    }

}
