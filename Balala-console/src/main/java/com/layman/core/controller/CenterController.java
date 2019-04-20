package com.layman.core.controller;

import com.layman.core.bean.TestTb;
import com.layman.core.service.TestTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//    @Autowired
//    private TestTbService testTbService;
//
//    @RequestMapping(value = "/test/index.do")
//    public String test(){
//        TestTb testTb = new TestTb();
//        testTb.setName("啊啊啊啊");
//        testTb.setBirthday(new Date());
//        testTbService.insertTestTb(testTb);
//        return "/index";
//    }
    @RequestMapping(value = "/index.do")
    public String index(Model model){
        return "index";
    }
    @RequestMapping(value = "/top.do")
    public String top(Model model){
        return "top";
    }
    @RequestMapping(value = "/main.do")
    public String main(Model model){
        return "main";
    }
    @RequestMapping(value = "/left.do")
    public String left(Model model){
        return "left";
    }
    @RequestMapping(value = "/right.do")
    public String right(Model model){
        return "right";
    }

    @RequestMapping("/frame/product_main")
    public String product_main(Model model) {
        return "frame/product_main";
    }

    @RequestMapping("/product/list")
    public String product_list(){
        return "product/list";
    }

    @RequestMapping("/frame/product_left")
    public String product_left() {
        return "frame/product_left";
    }
}
