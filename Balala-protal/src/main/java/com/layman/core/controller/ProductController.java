package com.layman.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ProductController
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/5/4 21:07
 * @Version 3.0
 **/
@Controller
public class ProductController {


    // 去首页
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
