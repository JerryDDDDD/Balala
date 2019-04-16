package com.layman.core.controller;

import com.layman.core.bean.TestTb;
import com.layman.core.service.TestTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

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

    @Autowired
    private TestTbService testTbService;

    @RequestMapping(value = "/test/index.do")
    public String test(){
        TestTb testTb = new TestTb();
        testTb.setName("啊啊啊啊");
        testTb.setBirthday(new Date());
        testTbService.insertTestTb(testTb);
        return "/index";
    }
}
