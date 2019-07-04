package com.layman.core.controller;

import cn.itcast.common.page.Pagination;
import com.layman.core.bean.product.Brand;
import com.layman.core.bean.product.Color;
import com.layman.core.bean.product.Product;
import com.layman.core.bean.product.Sku;
import com.layman.core.service.cms.CmsService;
import com.layman.core.service.product.BrandService;
import com.layman.core.service.solr.SearchService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName ProductController
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/5/4 21:07
 * @Version 3.0
 **/
@Controller
public class ProductController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CmsService cmsService;

    // 去首页
    @RequestMapping("/")
    public String index() {
        return "index";
    }


    // 搜索
    @RequestMapping("/search")
    public String search(Integer pageNo, String keyword, Long brandId, String price, Model model) throws SolrServerException {
        //List<Product> products = searchService.selectProductListByQuery(keyword);
        // 查询结果集
        List<Brand> brands = brandService.selectBrandListFromRedis();
        model.addAttribute("brands", brands);
        model.addAttribute("brandId", brandId);
        model.addAttribute("price", price);

        Pagination pagination = searchService.selectPaginationByQuery(pageNo, keyword);
        model.addAttribute("pagination", pagination);
        return "search";
    }



    // 去商品详情页面
    @RequestMapping(value = "/product/detail")
    public String detail(Long id, Model model) {
        // 返回商品
        Product product = cmsService.selectProductById(id);
        // sku
        List<Sku> skus = cmsService.selectSkuListByProductId(id);

        // 遍历一遍去掉颜色重复
        Set<Color> colors = new HashSet<>();
        for (Sku sku : skus) {
            colors.add(sku.getColor());
        }
        model.addAttribute("product", product);
        model.addAttribute("skus", skus);
        model.addAttribute("colors", colors);
        return "product";
    }
}
