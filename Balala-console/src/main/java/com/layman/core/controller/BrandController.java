package com.layman.core.controller;

import cn.itcast.common.page.Pagination;
import com.layman.core.bean.product.Brand;
import com.layman.core.service.product.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName BrandController
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/18 22:30
 * @Version 3.0
 **/
@Controller
public class BrandController {

    @Autowired
    private BrandService brandService;
    // 查询
    @RequestMapping(value = "/brand/list.do")
    public String list(String name, Integer isDisplay, Integer pageNo, Model model) {

        Pagination pagination = brandService.selectPaginationByQuery(name, isDisplay, pageNo);

        model.addAttribute("pagination", pagination);
        model.addAttribute("name", name);
        if (null != isDisplay) {
            model.addAttribute("isDisplay", isDisplay);
        } else {
            model.addAttribute("isDisplay", 1);
        }
        return "brand/list";
    }

    // 去修改页面
    @RequestMapping("/brand/toEdit.do")
    public String toEdit(Model model, Long id){

        Brand brand = brandService.selectBrandById(id);
        model.addAttribute("brand", brand);
        return "brand/edit";
    }

    // 修改
    @RequestMapping(value = "/brand/edit.do")
    public String edit(Brand brand) {

        brandService.updateBrandById(brand);
        // 重定向
        return "redirect:/brand/list.do";
    }
}
