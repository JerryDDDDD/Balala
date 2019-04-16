package com.layman.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName CenterController
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/16 16:28
 * @Version 3.0
 **/
@Controller
@RequestMapping("/center")
public class CenterController {

    @RequestMapping(value = "/test/index.do")
    public String test(){
        return "/index";
    }
}
