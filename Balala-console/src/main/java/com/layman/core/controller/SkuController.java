package com.layman.core.controller;

import com.layman.core.bean.product.Sku;
import com.layman.core.service.product.SkuService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName SkuController
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/5/4 16:13
 * @Version 3.0
 **/
@Controller
public class SkuController {

    @Autowired
    private SkuService skuService;

    @RequestMapping("/sku/list.do")
    public String list(Long productId, Model model) {

        List<Sku> skus = skuService.selectSkuByProductId(productId);
        model.addAttribute("skus", skus);
        return "sku/list";
    }

    @RequestMapping("/sku/addSku.do")
    public void addSku(Sku sku, HttpServletResponse response) throws IOException {
        skuService.updateSkuById(sku);
        JSONObject object = new JSONObject();
        object.put("message", "保存成功!");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(object.toString());
    }
}
