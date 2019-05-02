package com.layman.core.controller;

import cn.itcast.common.page.Pagination;
import com.layman.core.bean.product.Brand;
import com.layman.core.bean.product.Color;
import com.layman.core.service.product.BrandService;
import com.layman.core.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName ProductController
 * @Description 商品管理
 * @Author 叶泽文
 * @Data 2019/4/30 20:22
 * @Version 3.0
 **/
@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    BrandService brandService;

    // 查询
    @RequestMapping("/product/list.do")
    public String list(Integer pageNo, String name, Long brandId, Boolean isShow, Model model) {

        // 品牌结果集
        List<Brand> brands = brandService.selectBrandListByQuery(1);
        model.addAttribute("brands", brands);


        Pagination pagination = productService.selectPaginationByQuery(pageNo, name, brandId, isShow);
        model.addAttribute("pagination", pagination);
        model.addAttribute("name", name);
        model.addAttribute("brandId", brandId);
        if (isShow != null) {
            model.addAttribute("isShow", isShow);
        } else {
            model.addAttribute("isShow", false);
        }

        return "product/list";
    }

    // 去商品添加
    @RequestMapping("/product/toAdd.do")
    public String toAdd(Model model) {

        List<Brand> brands = brandService.selectBrandListByQuery(1);
        model.addAttribute("brands", brands);

        List<Color> colors = productService.selectColorList();
        model.addAttribute("colors", colors);
        return "/product/add";
    }
}
