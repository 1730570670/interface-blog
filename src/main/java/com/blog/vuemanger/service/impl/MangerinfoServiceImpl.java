package com.blog.vuemanger.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.vuemanger.pojo.MangerInfo;
import com.blog.vuemanger.service.MangerinfoService;
import com.blog.vuemanger.mapper.MangerinfoMapper;
import org.springframework.stereotype.Service;

/**
* @author Admin
* @description 针对表【mangerinfo】的数据库操作Service实现
* @createDate 2022-05-07 20:50:46
*/
@Service
public class MangerinfoServiceImpl extends ServiceImpl<MangerinfoMapper, MangerInfo>
    implements MangerinfoService{

}




