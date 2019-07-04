package com.layman.core.service;

import com.layman.core.bean.product.Color;
import com.layman.core.bean.product.Product;
import com.layman.core.bean.product.Sku;
import com.layman.core.bean.product.SkuQuery;
import com.layman.core.dao.product.ColorDao;
import com.layman.core.dao.product.ProductDao;
import com.layman.core.dao.product.SkuDao;
import com.layman.core.service.cms.CmsService;
import com.layman.core.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CmsServiceImpl
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/6/27 21:24
 * @Version 3.0
 **/
@Service("cmsService")
public class CmsServiceImpl implements CmsService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private SkuDao skuDao;

    @Autowired
    private ColorDao colorDao;

    // 查询商品
    public Product selectProductById(Long productId) {
        return productDao.selectByPrimaryKey(productId);
    }

    // 查询sku结果集 有货
    public List<Sku> selectSkuListByProductId(Long productId) {
        SkuQuery skuQuery = new SkuQuery();
        skuQuery.createCriteria().andProductIdEqualTo(productId).andStockGreaterThan(0);
        List<Sku> skus = skuDao.selectByExample(skuQuery);
        for (Sku sku : skus) {
            sku.setColor(colorDao.selectByPrimaryKey(sku.getColorId()));
        }
        return skus;
    }
}
